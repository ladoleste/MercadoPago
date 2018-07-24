package br.com.mercadolivre.pagamentos.features

import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import br.com.mercadolivre.pagamentos.R
import br.com.mercadolivre.pagamentos.features.amount.AmountFragment
import br.com.mercadolivre.pagamentos.features.cardissuers.CardFragment
import br.com.mercadolivre.pagamentos.features.installments.InstallmentsFragment
import br.com.mercadolivre.pagamentos.features.paymentmethod.PaymentMethodFragment
import br.com.mercadolivre.pagamentos.global.addFragment
import br.com.mercadolivre.pagamentos.global.getErrorMessage
import br.com.mercadolivre.pagamentos.global.replaceFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ChangeFragment {

    private var step = 1
    private val amountFragment = AmountFragment()
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        setSupportActionBar(toolbar)

        addFragment(amountFragment, R.id.fl_holder)
    }

    override fun onNextStep() {

        when {
            step == 0 -> {
                callSummary()
                replaceFragment(amountFragment, R.id.fl_holder)
                step++
            }
            step == 1 -> {
                replaceFragment(PaymentMethodFragment(), R.id.fl_holder)
                step++
            }
            step == 2 -> {
                replaceFragment(CardFragment(), R.id.fl_holder)
                step++
            }
            step == 3 -> {
                replaceFragment(InstallmentsFragment(), R.id.fl_holder)
                step++
            }
            step > 3 -> {
                step = 0
                onNextStep()
            }
        }
    }

    private fun callSummary() {
        viewModel.getSummary().observe(this, Observer(this::showSummary))
        viewModel.getError().observe(this, Observer(this::handleError))
        viewModel.loadSummary()
    }

    private fun handleError(it: Throwable?) {
        Snackbar.make(window.decorView.rootView, it.getErrorMessage(), Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry) {
                    viewModel.loadSummary()
                }
                .show()
    }

    private fun showSummary(list: List<String>?) {
        list?.let {

            val join = TextUtils.join("\n\n", it)

            AlertDialog.Builder(this)
                    .setMessage(join)
                    .setTitle(getString(R.string.summary))
                    .setPositiveButton(getString(R.string.ok), null)
                    .create().show()
        }

        viewModel.removeObservers(this)
    }

    override fun onStop() {
        viewModel.onStop()
        super.onStop()
    }

    override fun onBackPressed() {
        step--

        if (amountFragment.isVisible) {
            finish()
        }

        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate()
        } else {
            super.onBackPressed()
        }
    }
}
