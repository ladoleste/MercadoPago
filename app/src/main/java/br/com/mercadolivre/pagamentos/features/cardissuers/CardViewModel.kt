package br.com.mercadolivre.pagamentos.features.cardissuers

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val cardIssuers = MutableLiveData<List<CardIssuer>>()
    private val error = MutableLiveData<Throwable>()

    init {
        MlApplication.component.inject(this)
    }

    fun getCardIssuers(): LiveData<List<CardIssuer>> = cardIssuers

    fun getError(): LiveData<Throwable> = error

    fun loadCardIssuers() {

        val paymentMethod = repo.getPaymentMethod()

        cDispose.add(repo.loadCardIssuers(paymentMethod.id)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { t -> Timber.e(t) }
                .subscribe({
                    cardIssuers.postValue(it)
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

    fun removeObservers(owner: LifecycleOwner) {
        cardIssuers.removeObservers(owner)
    }
}
