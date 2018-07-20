package br.com.mercadolivre.pagamentos.repository

import br.com.mercadolivre.pagamentos.dto.PaymentMethod
import io.reactivex.Single

/**
 * Created by Anderson on 23/03/2018
 */
interface MlRepository {
    fun loadPaymentMethods(): Single<List<PaymentMethod>>
}