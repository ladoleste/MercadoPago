package br.com.mercadolivre.pagamentos.features.paymentmethod

import br.com.mercadolivre.pagamentos.dto.PaymentMethod


/**
 *Created by Anderson on 15/02/2018.
 */
interface ItemClick {
    fun onItemClick(item: PaymentMethod)
}