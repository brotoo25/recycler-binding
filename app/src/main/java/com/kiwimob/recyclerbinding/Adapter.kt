package com.kiwimob.recyclerbinding

import android.arch.paging.PagedListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_main.view.*

class Adapter : PagedListAdapter<User, Adapter.ViewHolder>(User.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class ViewHolder(parent : ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)) {

        var user : User? = null

        fun bindTo(item : User?) {
            this.user = item

            itemView.lbl_feed_item_name.text = user?.name
            itemView.lbl_feed_item_price.text= user?.email
        }
    }
}