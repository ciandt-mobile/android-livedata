package com.ciandt.livedataroom

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.RadioButton
import com.ciandt.livedataroom.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel
        binding.activity = this
    }

    fun onClickUpdateName(view: View) {
        if (newName.text.trim().isNotEmpty()) {
            viewModel.updateName(newName.text.toString())
        }
    }

    fun onUserRadioClicked(view: View) {
        if ((view as RadioButton).isChecked) {
            when (view.id) {
                R.id.user_1 -> viewModel.setUserId(1)
                R.id.user_2 -> viewModel.setUserId(2)
                R.id.user_3 -> viewModel.setUserId(3)
            }
        }
    }

    fun onClickDatabase(view: View) {
        viewModel.loadDatabaseUsers()
    }

    fun onClickRemote(view: View) {
        viewModel.loadRemoteUsers()
    }
}
