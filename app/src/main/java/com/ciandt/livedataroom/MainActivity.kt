package com.ciandt.livedataroom

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.list.observe(
            this,
            Observer { it?.let { listSize.text = "List size: ${it.size}" } })

        viewModel.page.observe(
            this,
            Observer { it?.let { pageSize.text = "Page size: ${it.size}" } })


        addFive.setOnClickListener {
            viewModel.addItems(5)
        }

        addTen.setOnClickListener {
            viewModel.addItems(10)
        }
    }
}
