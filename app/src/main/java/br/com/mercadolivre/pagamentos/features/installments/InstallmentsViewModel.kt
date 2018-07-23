package br.com.mercadolivre.pagamentos.features.installments

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import br.com.mercadolivre.pagamentos.dto.Installments
import br.com.mercadolivre.pagamentos.dto.PayerCostsItem
import br.com.mercadolivre.pagamentos.features.BaseViewModel
import br.com.mercadolivre.pagamentos.global.MlApplication
import br.com.mercadolivre.pagamentos.repository.MlRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

/**
 *Created by Anderson on 10/12/2017.
 */
class InstallmentsViewModel : BaseViewModel() {

    @Inject
    lateinit var repo: MlRepository

    private val installments = MutableLiveData<Installments>()
    private val payCosts = MutableLiveData<List<PayerCostsItem>>()
    private val error = MutableLiveData<Throwable>()

    init {
        MlApplication.component.inject(this)
    }

    fun getInstallments(): LiveData<Installments> = installments
    fun getPayCosts(): LiveData<List<PayerCostsItem>> = payCosts

    fun getError(): LiveData<Throwable> = error

    fun loadInstallments() {
        cDispose.add(repo.loadInstallments(5000.0, "visa", 296)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { t -> Timber.e(t) }
                .subscribe({
                    installments.postValue(it.first())
                    payCosts.postValue(it.first().payerCosts)
                }, {
                    error.postValue(it)
                }))
    }
}
