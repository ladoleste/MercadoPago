package br.com.mercadolivre.pagamentos.repository

import br.com.mercadolivre.pagamentos.dto.CardIssuer
import br.com.mercadolivre.pagamentos.dto.Installments
import br.com.mercadolivre.pagamentos.dto.PaymentMethod
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *Created by Anderson on 08/12/2017.
 */
interface MlApiService {
    @GET("v1/payment_methods")
    fun loadPaymentMethods(): Single<List<PaymentMethod>>

    @GET("v1/payment_methods/card_issuers")
    fun loadCardIssuers(@Query("payment_method_id") id: String): Single<List<CardIssuer>>

    @GET("v1/payment_methods/installments")
    fun loadInstallments(
            @Query("amount") amount: Int,
            @Query("payment_method_id") id: String,
            @Query("issuer.id") issuer: Int
    ): Single<List<Installments>>
}