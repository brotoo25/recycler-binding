package uy.com.arara.platform.adapters.recycler

import android.arch.paging.PagedList
import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.RelativeLayout
import com.kiwimob.recyclerbinding.recycler.BindingRecyclerViewAdapter
import com.kiwimob.recyclerbinding.recycler.GenericAdapter

private const val KEY_ITEMS = -123
private const val KEY_CLICK_HANDLER = -124

@BindingAdapter("items")
fun <T> setItems(recyclerView: RecyclerView, items: PagedList<T>?) {
    val adapter = recyclerView.adapter as GenericAdapter<T>?
    if (adapter != null && items != null) {
        adapter.submitList(items)
    } else {
        recyclerView.setTag(KEY_ITEMS, items)
    }
}

@BindingAdapter("clickHandler")
fun <T> setClickHandler(recyclerView: RecyclerView, handler: GenericAdapter.ClickHandler<T>) {
    val adapter = recyclerView.adapter as GenericAdapter<T>?
    if (adapter != null) {
        adapter.setClickHandler(handler)
    } else {
        recyclerView.setTag(KEY_CLICK_HANDLER, handler)
    }
}

@BindingAdapter("listItem")
fun <T> setListItem(recyclerView: RecyclerView, itemLayoutId: Int) {
    val items = recyclerView.getTag(KEY_ITEMS) as PagedList<T>?
    val clickHandler = recyclerView.getTag(KEY_CLICK_HANDLER) as GenericAdapter.ClickHandler<T>?
    val adapter = GenericAdapter()
    if (clickHandler != null) {
        adapter.setClickHandler(clickHandler)
    }
    recyclerView.adapter = adapter
}