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
import br.com.mercadolivre.pagamentos.dto.CardIssuer
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

    private fun loadData(cardIssuers: List<CardIssuer>?) {
        progress_bar.visibility = View.GONE
        cardIssuers?.let {
            rv_payments.layoutManager = LinearLayoutManager(requireActivity())
            rv_payments.adapter = CardAdapter(cardIssuers) {
                val requireActivity = requireActivity()
                if (requireActivity is ChangeFragment) {
                    viewModel.saveCardIssuer(it)
                    requireActivity.onNextStep()
                }
            }
        }
    }

    private fun handleError(it: Throwable?) {

        progress_bar.visibility = View.GONE

        Snackbar.make(activity!!.window.decorView.rootView, it.getErrorMessage(), Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry) {
                    progress_bar.visibility = View.VISIBLE
                    viewModel.loadCardIssuers()
                }
                .show()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadCardIssuers()
    }

    override fun onStop() {
        viewModel.onStop()
        viewModel.removeObservers(this)
        super.onStop()
    }
}
