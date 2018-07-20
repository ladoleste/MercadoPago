package br.com.mercadolivre.pagamentos.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.mercadolivre.pagamentos.R
import br.com.mercadolivre.pagamentos.dto.PaymentMethods
import br.com.mercadolivre.pagamentos.global.getErrorMessage
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.getPaymentsMethods().observe(this, Observer(this::loadData))
        viewModel.getError().observe(this, Observer(this::handleError))

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    private fun loadData(payMethods: PaymentMethods?) {
        progress_bar.visibility = View.GONE

        payMethods?.let {
            tv_text.text = payMethods.name
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
}
