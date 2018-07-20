package br.com.mercadolivre.pagamentos.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import br.com.mercadolivre.pagamentos.dto.PaymentMethods
import br.com.mercadolivre.pagamentos.global.MlApplication
import br.com.mercadolivre.pagamentos.repository.MlRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

/**
 *Created by Anderson on 10/12/2017.
 */
class MainViewModel : BaseViewModel() {

    @Inject
    lateinit var repo: MlRepository

    private val payMethods = MutableLiveData<PaymentMethods>()
    private val error = MutableLiveData<Throwable>()

    init {
        MlApplication.component.inject(this)
    }

    fun loadPaymentsMethods(): LiveData<PaymentMethods> = payMethods
    fun loadError(): LiveData<Throwable> = error

    fun getPaymentsMethods() {
        cDispose.add(repo.getPaymentsMethods()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { t -> Timber.e(t) }
                .subscribe({
                    payMethods.postValue(it)
                }, {
                    error.postValue(it)
                }))
    }
}