package br.com.mercadolivre.pagamentos.dto

import com.google.gson.annotations.SerializedName

data class Bin(@SerializedName("pattern")
               val pattern: String = "",
               @SerializedName("installments_pattern")
               val installmentsPattern: String = "",
               @SerializedName("exclusion_pattern")
               val exclusionPattern: String = "")