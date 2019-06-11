package br.com.mercadolivre.pagamentos.features

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.mercadolivre.pagamentos.R
import br.com.mercadolivre.pagamentos.global.MlApplication
import br.com.mercadolivre.pagamentos.repository.MlRepository
import java.text.NumberFormat
import javax.inject.Inject


/**
 *Created by Anderson on 10/12/2017.
 */
class MainViewModel : BaseViewModel() {

    @Inject
    lateinit var repo: MlRepository

    private val error = MutableLiveData<Throwable>()
    private val summary = MutableLiveData<List<String>>()

    init {
        MlApplication.component.inject(this)
    }

    fun getError(): LiveData<Throwable> = error

    fun getSummary(): LiveData<List<String>> {
        return summary
    }

    fun loadSummary() {
        val amount = repo.getAmount()
        val cardIssuer = repo.getCardIssuer()
        val payCost = repo.getPayCost()
        val paymentMethod = repo.getPaymentMethod()

        if (amount > 0) {
            val summaryList = mutableListOf<String>()

            summaryList.add(MlApplication.instance.getString(R.string.summary_amount, NumberFormat.getCurrencyInstance().format(amount)))
            summaryList.add(MlApplication.instance.getString(R.string.summary_issuer, cardIssuer.name))
            summaryList.add(MlApplication.instance.getString(R.string.summary_payment_method, paymentMethod.name))
            summaryList.add(MlApplication.instance.getString(R.string.summary_pay_cost, payCost.recommendedMessage))
            summary.postValue(summaryList)
        }
    }

    fun removeObservers(owner: LifecycleOwner) {
        error.removeObservers(owner)
        summary.removeObservers(owner)
    }
}
