package br.com.mercadolivre.pagamentos.repository

import br.com.mercadolivre.pagamentos.dto.CardIssuer
import br.com.mercadolivre.pagamentos.dto.Installments
import br.com.mercadolivre.pagamentos.dto.PayerCostsItem
import br.com.mercadolivre.pagamentos.dto.PaymentMethod
import io.reactivex.Single

/**
 * Created by Anderson on 23/03/2018
 */
interface MlRepository {
    fun loadPaymentMethods(): Single<List<PaymentMethod>>
    fun loadCardIssuers(id: String): Single<List<CardIssuer>>
    fun loadInstallments(amount: Int, payment_method_id: String, issuer: Int): Single<List<Installments>>

    fun saveAmount(amount: Int)
    fun saveCardIssuer(cardIssuer: CardIssuer)
    fun savePaymentMethod(paymentMethod: PaymentMethod)
    fun savePayCost(payCost: PayerCostsItem)

    fun getAmount(): Int
    fun getCardIssuer(): CardIssuer
    fun getPaymentMethod(): PaymentMethod
    fun getPayCost(): PayerCostsItem
}