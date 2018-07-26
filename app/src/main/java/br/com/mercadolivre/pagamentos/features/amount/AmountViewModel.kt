package br.com.mercadolivre.pagamentos.features.amount

import android.arch.lifecycle.LifecycleOwner
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

    init {
        MlApplication.component.inject(this)
    }

    fun getError(): LiveData<Throwable> = error

    fun saveAmount(amount: Int) {
        try {
            repo.saveAmount(amount)
        } catch (e: Exception) {
            Timber.e(e)
            error.postValue(e)
        }
    }

    fun removeObservers(owner: LifecycleOwner) {
        error.removeObservers(owner)
    }
}
