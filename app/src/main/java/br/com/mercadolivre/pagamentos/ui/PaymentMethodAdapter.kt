package br.com.mercadolivre.pagamentos.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import br.com.mercadolivre.pagamentos.R
import br.com.mercadolivre.pagamentos.dto.PaymentMethod
import br.com.mercadolivre.pagamentos.global.inflate
import br.com.mercadolivre.pagamentos.global.loadImage
import kotlinx.android.synthetic.main.item_payment.view.*


class PaymentMethodAdapter(_items: List<PaymentMethod>, private val listener: (PaymentMethod) -> Unit) : RecyclerView.Adapter<PaymentMethodAdapter.ViewHolder>() {

    private val items = mutableListOf<PaymentMethod>()

    init {
        items.addAll(_items)
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PaymentMethodAdapter.ViewHolder(parent.inflate(R.layout.item_payment))

    override fun onBindViewHolder(holder: PaymentMethodAdapter.ViewHolder, position: Int) = holder.bind(items[position], listener)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: PaymentMethod, listener: (PaymentMethod) -> Unit) = with(itemView) {
            tv_text.text = item.name
            iv_image.loadImage(item.thumbnail)
            setOnClickListener { listener(item) }
        }
    }
}