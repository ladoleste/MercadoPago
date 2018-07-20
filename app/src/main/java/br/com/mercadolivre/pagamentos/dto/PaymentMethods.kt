package br.com.mercadolivre.pagamentos.dto

import com.google.gson.annotations.SerializedName

data class PaymentMethods(@SerializedName("settings")
                          val settings: List<SettingsItem>?,
                          @SerializedName("thumbnail")
                          val thumbnail: String = "",
                          @SerializedName("deferred_capture")
                          val deferredCapture: String = "",
                          @SerializedName("secure_thumbnail")
                          val secureThumbnail: String = "",
                          @SerializedName("min_allowed_amount")
                          val minAllowedAmount: Int = 0,
                          @SerializedName("processing_modes")
                          val processingModes: List<String>?,
                          @SerializedName("name")
                          val name: String = "",
                          @SerializedName("id")
                          val id: String = "",
                          @SerializedName("additional_info_needed")
                          val additionalInfoNeeded: List<String>?,
                          @SerializedName("payment_type_id")
                          val paymentTypeId: String = "",
                          @SerializedName("status")
                          val status: String = "",
                          @SerializedName("max_allowed_amount")
                          val maxAllowedAmount: Int = 0,
                          @SerializedName("accreditation_time")
                          val accreditationTime: Int = 0)