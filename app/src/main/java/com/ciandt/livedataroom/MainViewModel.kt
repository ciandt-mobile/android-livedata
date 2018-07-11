package com.ciandt.livedataroom

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val list : LiveData<List<Int>> = MutableLiveData()

    private val _list = mutableListOf<Int>()

    fun addItems(size: Int) {
        val l = mutableListOf<Int>()

        for (i in 1..size) {
            l.add(i)
        }

        _list.addAll(l)

        (list as MutableLiveData).value = _list
    }

}