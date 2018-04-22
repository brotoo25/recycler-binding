package com.kiwimob.recyclerbinding

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.kiwimob.recyclerbinding.recycler.BindingRecyclerViewAdapter

class MainViewModel : ViewModel() {

    var users : LiveData<PagedList<User>>

    init {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(8)
                .setPageSize(8)
                .build()
        val factory = DataSourceFactory()
        users =  LivePagedListBuilder(factory, config).build()
    }

    fun productSelected(): BindingRecyclerViewAdapter.ClickHandler<User> {
        return BindingRecyclerViewAdapter.ClickHandler {

        }
    }
}