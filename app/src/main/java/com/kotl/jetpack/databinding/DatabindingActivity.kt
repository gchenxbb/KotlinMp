package com.kotl.jetpack.databinding

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kotl.jetpack.R

class DatabindingActivity : AppCompatActivity() {
    private val bindViewModel: BindViewModel by viewModels {
        providerBindViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = DataBindingUtil.setContentView<ActivityDatabindingBinding>(this, R.layout.activity_databinding)
        binding.lifecycleOwner = this

        binding.viewModel = bindViewModel;
        bindViewModel.editValue.value = "13162683391"

        var neam = BindBean()
        bindViewModel.bindValue.value = neam

        binding.callback = object : Callback {
            override fun set() {
                neam.phone.set("13162683391")
                neam.pwd.set("pwd1234")
            }

            override fun get() {
                Toast.makeText(this@DatabindingActivity, bindViewModel.editValue.value, Toast.LENGTH_SHORT).show()
            }


            override fun login() {
                val phone = bindViewModel.bindValue.value!!.phone.get()
                val pwd = bindViewModel.bindValue.value!!.pwd.get()
                Toast.makeText(this@DatabindingActivity, "phone:$phone,\npwd:$pwd", Toast.LENGTH_SHORT).show()
            }
        }


    }

    fun providerBindViewModelFactory(activity: DatabindingActivity): BindViewModelFactory {
        return BindViewModelFactory(activity)
    }

    interface Callback {
        fun set()
        fun get()
        fun login()
    }
}