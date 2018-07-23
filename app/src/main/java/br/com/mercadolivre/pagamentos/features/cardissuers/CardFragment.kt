package br.com.mercadolivre.pagamentos.features.cardissuers

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
import br.com.mercadolivre.pagamentos.dto.Bank
import br.com.mercadolivre.pagamentos.features.ChangeFragment
import br.com.mercadolivre.pagamentos.global.getErrorMessage
import kotlinx.android.synthetic.main.fragment_payment_method.*

/**
 * A placeholder fragment containing a simple view.
 */
class CardFragment : Fragment() {

    private lateinit var viewModel: CardViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this).get(CardViewModel::class.java)
        viewModel.getCardIssuers().observe(this, Observer(this::loadData))
        viewModel.getError().observe(this, Observer(this::handleError))

        return inflater.inflate(R.layout.fragment_payment_method, container, false)
    }

    private fun loadData(banks: List<Bank>?) {
        progress_bar.visibility = View.GONE
        banks?.let {
            rv_payments.layoutManager = LinearLayoutManager(requireActivity())
            rv_payments.adapter = CardAdapter(banks) {
                val requireActivity = requireActivity()
                if (requireActivity is ChangeFragment)
                    requireActivity.onChangeFragment(it.id)
            }
        }
    }

    private fun handleError(it: Throwable?) {

        progress_bar.visibility = View.GONE

        Snackbar.make(activity!!.window.decorView.rootView, it.getErrorMessage(), Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry) {
                    progress_bar.visibility = View.VISIBLE
                    viewModel.loadCardIssuers("visa")
                }
                .show()
    }

    override fun onResume() {
        super.onResume()
        arguments?.getString("id")?.let {
            viewModel.loadCardIssuers(it)
        }
    }

    override fun onStop() {
        viewModel.onStop()
        super.onStop()
    }
}
