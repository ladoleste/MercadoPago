package br.com.mercadolivre.pagamentos.features.cardissuers

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import br.com.mercadolivre.pagamentos.dto.CardIssuer
import br.com.mercadolivre.pagamentos.features.BaseViewModel
import br.com.mercadolivre.pagamentos.global.MlApplication
import br.com.mercadolivre.pagamentos.repository.MlRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

/**
 *Created by Anderson on 10/12/2017.
 */
class CardViewModel : BaseViewModel() {

    @Inject
    lateinit var repo: MlRepository

    private val payMethods = MutableLiveData<List<CardIssuer>>()
    private val error = MutableLiveData<Throwable>()

    init {
        MlApplication.component.inject(this)
    }

    fun getCardIssuers(): LiveData<List<CardIssuer>> = payMethods

    fun getError(): LiveData<Throwable> = error

    fun loadCardIssuers() {

        val paymentMethod = repo.getPaymentMethod()

        cDispose.add(repo.loadCardIssuers(paymentMethod.id)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { t -> Timber.e(t) }
                .subscribe({
                    payMethods.postValue(it)
                }, {
                    error.postValue(it)
                }))
    }

    fun saveCardIssuer(cardIssuer: CardIssuer) {
        try {
            repo.saveCardIssuer(cardIssuer)
        } catch (e: Exception) {
            error.postValue(e)
        }
    }
}
