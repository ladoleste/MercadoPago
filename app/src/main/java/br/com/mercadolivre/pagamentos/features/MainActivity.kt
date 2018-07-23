package br.com.mercadolivre.pagamentos.features

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.mercadolivre.pagamentos.R
import br.com.mercadolivre.pagamentos.features.amount.AmountFragment
import br.com.mercadolivre.pagamentos.features.cardissuers.CardFragment
import br.com.mercadolivre.pagamentos.features.installments.InstallmentsFragment
import br.com.mercadolivre.pagamentos.features.paymentmethod.PaymentMethodFragment
import br.com.mercadolivre.pagamentos.global.addFragment
import br.com.mercadolivre.pagamentos.global.replaceFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ChangeFragment {

    var step = 0

    override fun onChangeFragment(id: String) {

        when {
            step == 0 -> {
                val fragment = PaymentMethodFragment()
                val bundle = Bundle()
                bundle.putString("id", id)
                fragment.arguments = bundle
                replaceFragment(fragment, R.id.fl_holder)
            }
            step == 1 -> {
                val fragment = CardFragment()
                val bundle = Bundle()
                bundle.putString("id", id)
                fragment.arguments = bundle
                replaceFragment(fragment, R.id.fl_holder)
            }
            step > 1 -> {
                val fragment = InstallmentsFragment()
                val bundle = Bundle()
                bundle.putString("id", id)
                fragment.arguments = bundle
                replaceFragment(fragment, R.id.fl_holder)
            }
        }
        step++
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        addFragment(AmountFragment(), R.id.fl_holder)

        fab.setOnClickListener { _ ->
            onChangeFragment("visa")
        }
    }
}
