package br.com.mercadolivre.pagamentos.repository

import br.com.mercadolivre.pagamentos.dto.PaymentMethods
import io.reactivex.Single
import retrofit2.http.GET

/**
 *Created by Anderson on 08/12/2017.
 */
interface MlApiService {
    @GET("movie/popular")
    fun getPaymentsMethods(): Single<PaymentMethods>
}