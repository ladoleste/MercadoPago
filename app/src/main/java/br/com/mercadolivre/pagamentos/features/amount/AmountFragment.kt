package br.com.mercadolivre.pagamentos.features.amount

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.mercadolivre.pagamentos.R
import br.com.mercadolivre.pagamentos.features.ChangeFragment
import br.com.mercadolivre.pagamentos.global.getErrorMessage
import kotlinx.android.synthetic.main.fragment_amount.*

/**
 * A placeholder fragment containing a simple view.
 */
class AmountFragment : Fragment() {

    private lateinit var viewModel: AmountViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(AmountViewModel::class.java)
        viewModel.getError().observe(this, Observer(this::handleError))
        return inflater.inflate(R.layout.fragment_amount, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bt_next.setOnClickListener {

            val mainActivity = requireActivity()
            if (mainActivity is ChangeFragment) {
                val amount = et_amount.text.toString().toDoubleOrNull()
                if (amount == null || amount == 0.0) {
                    et_amount.error = getString(R.string.amount_validation)
                } else {
                    viewModel.saveAmount(amount)
                    mainActivity.onNextStep()
                }
            }
        }
    }

    private fun handleError(it: Throwable?) {
        Snackbar.make(activity!!.window.decorView.rootView, it.getErrorMessage(), Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry) {
                    viewModel.saveAmount(et_amount.text.toString().toDouble())
                }
                .show()
    }

    override fun onStop() {
        viewModel.removeObservers(this)
        viewModel.onStop()
        super.onStop()
    }
}
