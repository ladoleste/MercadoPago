package br.com.mercadolivre.pagamentos.features.cardissuers

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import br.com.mercadolivre.pagamentos.dto.Bank
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

    private val payMethods = MutableLiveData<List<Bank>>()
    private val error = MutableLiveData<Throwable>()

    init {
        MlApplication.component.inject(this)
    }

    fun getCardIssuers(): LiveData<List<Bank>> = payMethods

    fun getError(): LiveData<Throwable> = error

    fun loadCardIssuers(id: String) {
        cDispose.add(repo.loadCardIssuers(id)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { t -> Timber.e(t) }
                .subscribe({
                    payMethods.postValue(it)
                }, {
                    error.postValue(it)
                }))
    }

    fun saveBank(bank: Bank) {
        try {
            repo.saveBank(bank)
        } catch (e: Exception) {
            error.postValue(e)
        }
    }
}