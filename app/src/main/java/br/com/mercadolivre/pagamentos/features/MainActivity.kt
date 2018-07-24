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

    var step = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        addFragment(AmountFragment(), R.id.fl_holder)
    }

    override fun onNextStep() {

        when {
            step == 0 -> {
                replaceFragment(AmountFragment(), R.id.fl_holder)
            }
            step == 1 -> {
                replaceFragment(PaymentMethodFragment(), R.id.fl_holder)
            }
            step == 2 -> {
                replaceFragment(CardFragment(), R.id.fl_holder)
            }
            step == 3 -> {
                replaceFragment(InstallmentsFragment(), R.id.fl_holder)
            }
            step > 3 -> {
                step = 0
                onNextStep()
            }
        }
        step++
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate()
        } else {
            step--
            super.onBackPressed()
        }
    }
}
