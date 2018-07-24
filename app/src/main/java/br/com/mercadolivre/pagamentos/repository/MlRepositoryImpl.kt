package br.com.mercadolivre.pagamentos.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import br.com.mercadolivre.pagamentos.dto.CardIssuer
import br.com.mercadolivre.pagamentos.dto.PayerCostsItem
import br.com.mercadolivre.pagamentos.dto.PaymentMethod
import br.com.mercadolivre.pagamentos.global.MlApplication
import com.google.gson.Gson
import javax.inject.Inject

private const val AMOUNT = "amount"
private const val CARD_ISSUER = "card_issuer"
private const val PAYMENT_METHOD = "paymentMethod"
private const val PAY_COST = "payCost"

/**
 * Created by Anderson on 23/03/2018
 */
class MlRepositoryImpl : MlRepository {


    @Inject
    lateinit var api: MlApiService

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    init {
        MlApplication.component.inject(this)
    }

    override fun loadPaymentMethods() = api.loadPaymentMethods()

    override fun loadCardIssuers(id: String) = api.loadCardIssuers(id)

    override fun loadInstallments(amount: Double, payment_method_id: String, issuer: Int) = api.loadInstallments(amount, payment_method_id, issuer)

    override fun saveAmount(amount: Double) {
        sharedPreferences.edit {
            putLong(AMOUNT, amount.toLong())
        }
    }

    override fun saveCardIssuer(cardIssuer: CardIssuer) {
        sharedPreferences.edit {
            putString(CARD_ISSUER, Gson().toJson(cardIssuer))
        }
    }

    override fun savePaymentMethod(paymentMethod: PaymentMethod) {
        sharedPreferences.edit {
            putString(PAYMENT_METHOD, Gson().toJson(paymentMethod))
        }
    }

    override fun savePayCost(payCost: PayerCostsItem) {
        sharedPreferences.edit {
            putString(PAY_COST, Gson().toJson(payCost))
        }
    }

    override fun getPaymentMethod(): PaymentMethod {
        val r = sharedPreferences.getString(PAYMENT_METHOD, "")
        return Gson().fromJson(r, PaymentMethod::class.java)
    }

    override fun getAmount(): Double {
        return sharedPreferences.getLong(AMOUNT, 0).toDouble()
    }

    override fun getCardIssuer(): CardIssuer {
        val r = sharedPreferences.getString(CARD_ISSUER, "")
        return Gson().fromJson(r, CardIssuer::class.java)
    }

    override fun getPayCost(): PayerCostsItem {
        val r = sharedPreferences.getString(PAY_COST, "")
        return Gson().fromJson(r, PayerCostsItem::class.java)
    }
}