package br.com.mercadolivre.pagamentos.features.cardissuers

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import br.com.mercadolivre.pagamentos.R
import br.com.mercadolivre.pagamentos.dto.Bank
import br.com.mercadolivre.pagamentos.global.inflate
import br.com.mercadolivre.pagamentos.global.loadImage
import kotlinx.android.synthetic.main.item_payment_method.view.*


class CardAdapter(_items: List<Bank>, private val listener: (Bank) -> Unit) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    private val items = mutableListOf<Bank>()

    init {
        items.addAll(_items)
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.item_payment_method))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Bank, listener: (Bank) -> Unit) = with(itemView) {
            tv_text.text = item.name
            iv_image.loadImage(item.thumbnail)
            setOnClickListener { listener(item) }
        }
    }
}