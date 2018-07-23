package br.com.mercadolivre.pagamentos.dto

import com.google.gson.annotations.SerializedName

data class Installments(@SerializedName("payment_method_id")
                        val paymentMethodId: String = "",
                        @SerializedName("payer_costs")
                        val payerCosts: List<PayerCostsItem>?,
                        @SerializedName("merchant_account_id")
                        val merchantAccountId: Any? = null,
                        @SerializedName("processing_mode")
                        val processingMode: String = "",
                        @SerializedName("payment_type_id")
                        val paymentTypeId: String = "",
                        @SerializedName("issuer")
                        val issuer: Issuer)