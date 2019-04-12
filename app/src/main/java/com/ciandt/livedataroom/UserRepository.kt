package com.ciandt.livedataroom

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

class UserRepository {

    private val users = listOf(User(1, "John"), User(2, "Peter"), User(3, "Mary"))

    private val _databaseUsers = MutableLiveData<List<User>>()
    private val _remoteUsers = MutableLiveData<List<User>>()

    private val selectedUser = MutableLiveData<User>()

    val databaseUsers: LiveData<List<User>>
        get() = _databaseUsers

    val remoteUsers: LiveData<List<User>>
        get() = _remoteUsers

    fun getUser(id: Int): LiveData<User> {

        val user = users.find { it.id == id }

        user?.let {
            selectedUser.value = user
        }

        return selectedUser
    }

    fun updateSelectedName(id: Int, name: String) {

        val user = users.find { it.id == id }

        user?.let {
            user.name = name
            selectedUser.value = user
        }
    }

    fun loadDatabaseUsers() {
        _databaseUsers.value = listOf(User(1, "Lee"), User(2, "Tabler"), User(3, "Colon"))
    }

    fun loadRemoteUsers() {
        _remoteUsers.value = listOf(User(1, "Deena"), User(2, "Lillie"), User(3, "Kirstie"))
    }
}