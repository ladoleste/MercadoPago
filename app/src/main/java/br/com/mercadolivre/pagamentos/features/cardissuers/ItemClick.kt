package br.com.mercadolivre.pagamentos.features.cardissuers

import br.com.mercadolivre.pagamentos.dto.Bank

/**
 *Created by Anderson on 15/02/2018.
 */
interface ItemClick {
    fun onItemClick(item: Bank)
}