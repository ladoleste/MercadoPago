package br.com.mercadolivre.pagamentos.features.paymentmethod

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.mercadolivre.pagamentos.R
import br.com.mercadolivre.pagamentos.dto.PaymentMethod
import br.com.mercadolivre.pagamentos.features.ChangeFragment
import br.com.mercadolivre.pagamentos.features.MainActivity
import br.com.mercadolivre.pagamentos.global.getErrorMessage
import kotlinx.android.synthetic.main.fragment_payment_method.*

/**
 * A placeholder fragment containing a simple view.
 */
class PaymentMethodFragment : Fragment() {

    private lateinit var viewModel: PaymentMethodViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this).get(PaymentMethodViewModel::class.java)
        viewModel.getPaymentsMethods().observe(this, Observer(this::loadData))
        viewModel.getError().observe(this, Observer(this::handleError))

        return inflater.inflate(R.layout.fragment_payment_method, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = getString(R.string.title_payment_method)
    }

    private fun loadData(payMethods: List<PaymentMethod>?) {
        progress_bar.visibility = View.GONE
        payMethods?.let {
            rv_payments.layoutManager = LinearLayoutManager(requireActivity())
            rv_payments.adapter = PaymentMethodAdapter(payMethods) {
                val requireActivity = requireActivity()
                viewModel.savePaymentMethod(it)
                if (requireActivity is ChangeFragment)
                    requireActivity.onNextStep()
            }
        }
    }

    private fun handleError(it: Throwable?) {

        progress_bar.visibility = View.GONE

        Snackbar.make(activity!!.window.decorView.rootView, it.getErrorMessage(), Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry) {
                    progress_bar.visibility = View.VISIBLE
                    viewModel.loadPaymentsMethods()
                }
                .show()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadPaymentsMethods()
    }

    override fun onStop() {
        viewModel.removeObservers(this)
        viewModel.onStop()
        super.onStop()
    }
}
