package com.kiwimob.recyclerbinding.recycler

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class GenericAdapter<T>(diff: DiffUtil.ItemCallback<T>) :
        PagedListAdapter<T, GenericAdapter.ViewHolder>(diff),
        View.OnClickListener {

    companion object {
        const val ITEM_MODEL = -124
    }

    private var inflater: LayoutInflater? = null
    private var clickHandler: ClickHandler<T>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericAdapter.ViewHolder {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.context)
        }

        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater!!, viewType, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenericAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
//        holder.binding.setVariable(BR.data, item)
        holder.binding.root.setTag(ITEM_MODEL, item)
        holder.binding.root.setOnClickListener(this)
        holder.binding.executePendingBindings()
    }

    class ViewHolder constructor(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onClick(v: View?) {
        val item = v?.getTag(ITEM_MODEL) as T
        clickHandler?.onClick(item)
    }

    fun setClickHandler(clickHandler: ClickHandler<T>) {
        this.clickHandler = clickHandler
    }

    interface ClickHandler<in T> {
        fun onClick(model: T)
    }
}