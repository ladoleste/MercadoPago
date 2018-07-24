package br.com.mercadolivre.pagamentos.dto

import com.google.gson.annotations.SerializedName

data class PayerCostsItem(@SerializedName("installments")
                          val installments: Int = 0,
                          @SerializedName("installment_amount")
                          val installmentAmount: Double = 0.0,
                          @SerializedName("total_amount")
                          val totalAmount: Double = 0.0,
                          @SerializedName("recommended_message")
                          val recommendedMessage: String = "",
                          @SerializedName("installment_rate")
                          val installmentRate: Double = 0.0,
//                          @SerializedName("installment_rate_collector")
//                          val installmentRateCollector: List<String>?,
                          @SerializedName("discount_rate")
                          val discountRate: Int = 0,
                          @SerializedName("min_allowed_amount")
                          val minAllowedAmount: Int = 0,
//                          @SerializedName("labels")
//                          val labels: List<String>?,
                          @SerializedName("max_allowed_amount")
                          val maxAllowedAmount: Int = 0)