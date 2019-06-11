package br.com.mercadolivre.pagamentos.features.installments

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.mercadolivre.pagamentos.R
import br.com.mercadolivre.pagamentos.dto.PayerCostsItem
import br.com.mercadolivre.pagamentos.global.inflate
import kotlinx.android.synthetic.main.item_payment_method.view.*


class ItemCostAdapter(_items: List<PayerCostsItem>, private val listener: (PayerCostsItem) -> Unit) : RecyclerView.Adapter<ItemCostAdapter.ViewHolder>() {

    private val items = mutableListOf<PayerCostsItem>()

    init {
        items.addAll(_items)
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.item_paycost))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: PayerCostsItem, listener: (PayerCostsItem) -> Unit) = with(itemView) {
            tv_text.text = item.recommendedMessage
            setOnClickListener { listener(item) }
        }
    }
}