package br.com.mercadolivre.pagamentos.features

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.mercadolivre.pagamentos.R
import br.com.mercadolivre.pagamentos.features.amount.AmountFragment
import br.com.mercadolivre.pagamentos.features.paymentmethod.PaymentMethodFragment
import br.com.mercadolivre.pagamentos.global.addFragment
import br.com.mercadolivre.pagamentos.global.replaceFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        addFragment(AmountFragment(), R.id.fl_holder)

        fab.setOnClickListener { _ ->
            replaceFragment(PaymentMethodFragment(), R.id.fl_holder)
        }
    }
}
