package br.com.mercadolivre.pagamentos.repository

import br.com.mercadolivre.pagamentos.dto.Bank
import br.com.mercadolivre.pagamentos.dto.Installments
import br.com.mercadolivre.pagamentos.dto.PaymentMethod
import io.reactivex.Single

/**
 * Created by Anderson on 23/03/2018
 */
interface MlRepository {
    fun loadPaymentMethods(): Single<List<PaymentMethod>>
    fun loadCardIssuers(id: String): Single<List<Bank>>
    fun loadInstallments(amount: Double, id: String, issuer: Int): Single<List<Installments>>
    fun saveAmount(amount: Double)
}