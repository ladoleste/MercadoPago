package br.com.mercadolivre.pagamentos.features.installments

import br.com.mercadolivre.pagamentos.dto.Installments


/**
 *Created by Anderson on 15/02/2018.
 */
interface ItemClick {
    fun onItemClick(item: Installments)
}