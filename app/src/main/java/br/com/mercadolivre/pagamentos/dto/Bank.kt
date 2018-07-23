package br.com.mercadolivre.pagamentos.dto

import com.google.gson.annotations.SerializedName

data class Bank(@SerializedName("thumbnail")
                val thumbnail: String = "",
                @SerializedName("secure_thumbnail")
                val secureThumbnail: String = "",
                @SerializedName("name")
                val name: String = "",
                @SerializedName("merchant_account_id")
                val merchantAccountId: Any? = null,
                @SerializedName("processing_mode")
                val processingMode: String = "",
                @SerializedName("id")
                val id: String = "")