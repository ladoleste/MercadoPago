package br.com.mercadolivre.pagamentos.repository

import br.com.mercadolivre.pagamentos.global.MlApplication
import javax.inject.Inject

/**
 * Created by Anderson on 23/03/2018
 */
class MlRepositoryImpl : MlRepository {

    @Inject
    lateinit var api: MlApiService

    init {
        MlApplication.component.inject(this)
    }

    override fun loadPaymentMethods() = api.loadPaymentMethods()

    override fun loadCardIssuers(id: String) = api.loadCardIssuers(id)

    override fun loadInstallments(amount: Double, id: String, issuer: Int) = api.loadInstallments(amount, id, issuer)
}