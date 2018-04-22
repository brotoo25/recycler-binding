package com.kiwimob.recyclerbinding

import android.support.v7.util.DiffUtil

data class User(val id: String = "",
                val name: String = "",
                val email: String = "") {

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<User> = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(old: User, new: User): Boolean {
                return old.id === new.id
            }

            override fun areContentsTheSame(old: User, new: User): Boolean {
                return old.name == new.name
            }
        }
    }
}
