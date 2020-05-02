package com.raywenderlich.isell.adapter

import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raywenderlich.isell.R
import com.raywenderlich.isell.data.Item
import kotlinx.android.synthetic.main.layout_list_item.view.*

class ItemsAdapter(private val items:List<Item>)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(item:Item) = with(itemView){
            itemTitle.text = item.title
            itemImage.setImageResource(item.imageId)
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
        (holder as ViewHolder).bind(items[position])
    }
}