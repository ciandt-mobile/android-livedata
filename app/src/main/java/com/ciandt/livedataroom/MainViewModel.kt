package com.ciandt.livedataroom

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val list = MediatorLiveData<List<Int>>()

    val page: LiveData<List<Int>> = MutableLiveData()

    private val _list = mutableListOf<Int>()

    init {
        list.addSource(page) {
            page.value?.let {
                _list.addAll(it)
                list.value = _list
            }
        }

        (page as MutableLiveData).value = listOf()
    }

    fun addItems(size: Int) {
        val list = mutableListOf<Int>()

        for (i in 1..size) {
            list.add(i)
        }

        (page as MutableLiveData).value = list
    }

}