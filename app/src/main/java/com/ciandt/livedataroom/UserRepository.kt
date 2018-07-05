package com.ciandt.livedataroom

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

class UserRepository {

    private val users = listOf(User(1, "John"), User(2, "Peter"), User(3, "Mary"))

    private val databaseUsers = listOf(User(1, "Lee"), User(2, "Tabler"), User(3, "Colon"))
    private val remoteUsers = listOf(User(1, "Deena"), User(2, "Lillie"), User(3, "Kirstie"))

    fun getUser(id: Int): LiveData<User> {
        val result = MutableLiveData<User>()

        val user = users.find { it.id == id }

        user?.let {
            result.value = user
        }

        return result
    }

    fun getDatabaseUsers() = databaseUsers

    fun getRemoteUsers() = remoteUsers
}