package br.com.mercadolivre.pagamentos.features.amount

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import br.com.mercadolivre.pagamentos.dto.PaymentMethod
import br.com.mercadolivre.pagamentos.features.BaseViewModel
import br.com.mercadolivre.pagamentos.global.MlApplication
import br.com.mercadolivre.pagamentos.repository.MlRepository
import javax.inject.Inject

/**
 *Created by Anderson on 10/12/2017.
 */
class AmountViewModel : BaseViewModel() {

    @Inject
    lateinit var repo: MlRepository

    private val payMethods = MutableLiveData<List<PaymentMethod>>()
    private val error = MutableLiveData<Throwable>()

    init {
        MlApplication.component.inject(this)
    }

    fun getPaymentsMethods(): LiveData<List<PaymentMethod>> = payMethods

    fun getError(): LiveData<Throwable> = error

    fun saveAmount(amount: Double) {
        repo.saveAmount(amount)
    }
}
