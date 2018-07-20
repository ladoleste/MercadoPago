package br.com.mercadolivre.pagamentos.dto

import com.google.gson.annotations.SerializedName

data class CardNumber(@SerializedName("length")
                      val length: Int = 0,
                      @SerializedName("validation")
                      val validation: String = "")