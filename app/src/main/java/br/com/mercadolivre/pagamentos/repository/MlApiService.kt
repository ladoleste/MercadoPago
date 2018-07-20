package br.com.mercadolivre.pagamentos.repository

import br.com.mercadolivre.pagamentos.dto.PaymentMethod
import io.reactivex.Single
import retrofit2.http.GET

/**
 *Created by Anderson on 08/12/2017.
 */
interface MlApiService {
    @GET("v1/payment_methods")
    fun loadPaymentMethods(): Single<List<PaymentMethod>>
}