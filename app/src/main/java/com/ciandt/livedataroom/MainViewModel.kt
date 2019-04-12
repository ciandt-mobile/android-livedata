package com.ciandt.livedataroom

import android.arch.lifecycle.*

class MainViewModel : ViewModel() {
    private val repo = UserRepository()

    private val userId = MutableLiveData<Int>()

    private val user: LiveData<User> = Transformations.switchMap(userId) {
        repo.getUser(it)
    }

    val userName: LiveData<String> = Transformations.map(user) { it.name }

    val users = MediatorLiveData<String>().apply {

        addSource(repo.databaseUsers) {
            it?.let { list ->
                value = "${list.joinToString()} ${repo.remoteUsers.value?.joinToString() ?: ""}"
            }
        }

        addSource(repo.remoteUsers) {
            it?.let { list ->
                value = "${list.joinToString()} ${repo.databaseUsers.value?.joinToString() ?: ""}"
            }
        }
    }

    fun updateName(name: String) {
        userId.value?.let {
            repo.updateSelectedName(it, name)
        }
    }

    fun setUserId(id: Int) {
        userId.value = id
    }

    fun loadDatabaseUsers() {
        repo.loadDatabaseUsers()
    }

    fun loadRemoteUsers() {
        repo.loadRemoteUsers()
    }
}