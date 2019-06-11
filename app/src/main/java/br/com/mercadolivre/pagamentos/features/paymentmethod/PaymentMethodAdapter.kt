package br.com.mercadolivre.pagamentos.features.paymentmethod

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.mercadolivre.pagamentos.R
import br.com.mercadolivre.pagamentos.dto.PaymentMethod
import br.com.mercadolivre.pagamentos.global.inflate
import br.com.mercadolivre.pagamentos.global.loadImage
import kotlinx.android.synthetic.main.item_payment_method.view.*


class PaymentMethodAdapter(_items: List<PaymentMethod>, private val listener: (PaymentMethod) -> Unit) : RecyclerView.Adapter<PaymentMethodAdapter.ViewHolder>() {

    private val items = mutableListOf<PaymentMethod>()

    init {
        items.addAll(_items)
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.item_payment_method))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: PaymentMethod, listener: (PaymentMethod) -> Unit) = with(itemView) {
            tv_text.text = item.name
            iv_image.loadImage(item.thumbnail)
            setOnClickListener { listener(item) }
        }
    }
}