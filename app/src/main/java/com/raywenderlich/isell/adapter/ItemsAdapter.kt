package com.raywenderlich.isell.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raywenderlich.isell.R
import com.raywenderlich.isell.data.Item
import kotlinx.android.synthetic.main.layout_list_item.view.*

class ItemsAdapter(private val items: List<Item>,
                   private val clickListener: OnItemClickListener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    interface OnItemClickListener {
        fun onItemClick(item: Item, itemView: View)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Item, listener: OnItemClickListener) = with(itemView) {
            itemTitle.text = item.title
            itemImage.setImageResource(item.imageId)
            itemPrice.text = item.price.toString() + " $"
            setOnClickListener{
                listener.onItemClick(item, it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.layout_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(items[position], clickListener)
    }
}