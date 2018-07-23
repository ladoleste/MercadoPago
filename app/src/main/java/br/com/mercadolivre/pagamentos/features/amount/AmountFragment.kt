package br.com.mercadolivre.pagamentos.features.amount

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.mercadolivre.pagamentos.R
import br.com.mercadolivre.pagamentos.dto.PaymentMethod

/**
 * A placeholder fragment containing a simple view.
 */
class AmountFragment : Fragment() {

    private lateinit var viewModel: AmountViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this).get(AmountViewModel::class.java)
//        viewModel.getPaymentsMethods().observe(this, Observer(this::loadData))
//        viewModel.getError().observe(this, Observer(this::handleError))

        return inflater.inflate(R.layout.fragment_amount, container, false)
    }

    private fun loadData(payMethods: List<PaymentMethod>?) {

    }

    private fun handleError(it: Throwable?) {

    }

    override fun onResume() {
        super.onResume()
        //viewModel.loadPaymentsMethods()
    }
}
