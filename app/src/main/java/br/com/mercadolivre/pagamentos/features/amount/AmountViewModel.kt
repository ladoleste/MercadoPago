package br.com.mercadolivre.pagamentos.features.amount

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import br.com.mercadolivre.pagamentos.features.BaseViewModel
import br.com.mercadolivre.pagamentos.global.MlApplication
import br.com.mercadolivre.pagamentos.repository.MlRepository
import timber.log.Timber
import javax.inject.Inject

/**
 *Created by Anderson on 10/12/2017.
 */
class AmountViewModel : BaseViewModel() {

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

        val summaryList = mutableListOf<String>()
        summaryList.add("Amount: $amount")
        summaryList.add("Card Issumer: ${cardIssuer.name}")
        summaryList.add("Payment Method: ${paymentMethod.name}")
        summaryList.add("Pay Cost: ${payCost.recommendedMessage}")
        summary.postValue(summaryList)
    }

    fun saveAmount(amount: Double) {
        try {
            repo.saveAmount(amount)
        } catch (e: Exception) {
            Timber.e(e)
            error.postValue(e)
        }
    }
}
