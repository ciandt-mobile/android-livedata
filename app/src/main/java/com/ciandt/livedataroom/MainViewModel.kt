package com.ciandt.livedataroom

import android.arch.lifecycle.*

class MainViewModel : ViewModel() {
    private val repo = UserRepository()

    val simple: LiveData<String> = MutableLiveData()

    val map: LiveData<String> =
        Transformations.map(simple) { "Map (Convert to upper case): ${it.toUpperCase()}" }

    private val userId = MutableLiveData<Int>()

    val user: LiveData<User> = Transformations.switchMap(userId) {
        repo.getUser(it)
    }

    private val databaseUsers = MutableLiveData<List<User>>()
    private val remoteUsers = MutableLiveData<List<User>>()

    val users = MediatorLiveData<List<User>>()

    private val _users = mutableListOf<User>()

    init {
        users.value = mutableListOf()

        users.addSource(databaseUsers) {
            it?.let {
                _users.addAll(it)
                users.value = _users
            }
        }

        users.addSource(remoteUsers) {
            it?.let {
                _users.addAll(it)
                users.value = _users
            }
        }
    }

    fun setSimple() {
        (simple as MutableLiveData).value = "It's a simple LiveData"
    }

    fun setUserId(id: Int) {
        userId.value = id
    }

    fun loadDatabaseUsers() {
        databaseUsers.value = repo.getDatabaseUsers()
    }

    fun loadRemoteUsers() {
        remoteUsers.value = repo.getRemoteUsers()
    }
}