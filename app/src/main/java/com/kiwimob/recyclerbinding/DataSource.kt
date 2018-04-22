package com.kiwimob.recyclerbinding

import android.arch.paging.DataSource
import android.arch.paging.ItemKeyedDataSource
import com.google.firebase.firestore.FirebaseFirestore
import com.kiwimob.firestore.coroutines.await
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class DataSource(val firestore: FirebaseFirestore) : ItemKeyedDataSource<String, User>() {

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<User>) {
        launch(UI) {
            val users = firestore
                    .collection("users")
                    .orderBy("name")
                    .limit(params.requestedLoadSize.toLong())
                    .await(User::class.java)

            callback.onResult(users)
        }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<User>) {
        launch(UI) {
            val last = firestore.collection("users").document(params.key)
            val users = firestore
                    .collection("users")
                    .orderBy("name")
                    .startAfter(last.await())
                    .limit(params.requestedLoadSize.toLong())
                    .await(User::class.java)

            callback.onResult(users)
        }
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<User>) {

    }

    override fun getKey(item: User) = item.id
}

class DataSourceFactory : DataSource.Factory<String, User>() {

    override fun create(): DataSource<String, User> {
        return com.kiwimob.recyclerbinding.DataSource(FirebaseFirestore.getInstance())
    }
}