package br.com.mercadolivre.pagamentos.features.installments

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
import br.com.mercadolivre.pagamentos.dto.Installments
import br.com.mercadolivre.pagamentos.dto.PayerCostsItem
import br.com.mercadolivre.pagamentos.features.ChangeFragment
import br.com.mercadolivre.pagamentos.global.getErrorMessage
import kotlinx.android.synthetic.main.fragment_payment_method.*

/**
 * A placeholder fragment containing a simple view.
 */
class InstallmentsFragment : Fragment() {

    private lateinit var viewModel: InstallmentsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this).get(InstallmentsViewModel::class.java)
        viewModel.getInstallments().observe(this, Observer(this::loadData))
        viewModel.getPayCosts().observe(this, Observer(this::loadData2))
        viewModel.getError().observe(this, Observer(this::handleError))

        return inflater.inflate(R.layout.fragment_payment_method, container, false)
    }

    private fun loadData(installments: Installments?) {
        progress_bar.visibility = View.GONE
    }

    private fun loadData2(payCosts: List<PayerCostsItem>?) {
        progress_bar.visibility = View.GONE
        payCosts?.let {
            rv_payments.layoutManager = LinearLayoutManager(requireActivity())
            rv_payments.adapter = ItemCostAdapter(payCosts) {
                val requireActivity = requireActivity()
                if (requireActivity is ChangeFragment)
                    requireActivity.onChangeFragment("x")
            }
        }
    }

    private fun handleError(it: Throwable?) {

        progress_bar.visibility = View.GONE

        Snackbar.make(activity!!.window.decorView.rootView, it.getErrorMessage(), Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry) {
                    progress_bar.visibility = View.VISIBLE
                    viewModel.loadInstallments()
                }
                .show()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadInstallments()
    }
}
