package br.com.mercadolivre.pagamentos.dto

import com.google.gson.annotations.SerializedName

data class SecurityCode(@SerializedName("mode")
                        val mode: String = "",
                        @SerializedName("card_location")
                        val cardLocation: String = "",
                        @SerializedName("length")
                        val length: Int = 0)