package br.com.mercadolivre.pagamentos.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import br.com.mercadolivre.pagamentos.global.MlApplication
import javax.inject.Inject

/**
 * Created by Anderson on 23/03/2018
 */
class MlRepositoryImpl : MlRepository {

    @Inject
    lateinit var api: MlApiService

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    init {
        MlApplication.component.inject(this)
    }

    override fun loadPaymentMethods() = api.loadPaymentMethods()

    override fun loadCardIssuers(id: String) = api.loadCardIssuers(id)

    override fun loadInstallments(amount: Double, id: String, issuer: Int) = api.loadInstallments(amount, id, issuer)

    override fun saveAmount(amount: Double) {
        sharedPreferences.edit {
            putLong("amount", amount.toLong())
        }
    }
}