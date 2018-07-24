package br.com.mercadolivre.pagamentos.features.paymentmethod

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import br.com.mercadolivre.pagamentos.dto.PaymentMethod
import br.com.mercadolivre.pagamentos.features.BaseViewModel
import br.com.mercadolivre.pagamentos.global.MlApplication
import br.com.mercadolivre.pagamentos.repository.MlRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

/**
 *Created by Anderson on 10/12/2017.
 */
class PaymentMethodViewModel : BaseViewModel() {

    @Inject
    lateinit var repo: MlRepository

    private val payMethods = MutableLiveData<List<PaymentMethod>>()
    private val error = MutableLiveData<Throwable>()

    init {
        MlApplication.component.inject(this)
    }

    fun getPaymentsMethods(): LiveData<List<PaymentMethod>> = payMethods

    fun getError(): LiveData<Throwable> = error

    fun loadPaymentsMethods() {
        cDispose.add(repo.loadPaymentMethods()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { t -> Timber.e(t) }
                .subscribe({
                    payMethods.postValue(it)
                }, {
                    error.postValue(it)
                }))
    }

    fun savePaymentMethod(it: PaymentMethod) {
        repo.savePaymentMethod(it)
    }
}
