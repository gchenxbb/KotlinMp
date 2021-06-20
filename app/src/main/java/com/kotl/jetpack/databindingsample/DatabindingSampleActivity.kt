package com.kotl.jetpack.databindingsample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kotl.jetpack.R
import com.kotl.jetpack.databinding.ActivityDatabindingSampleBinding
import com.kotl.jetpack.databinding.BindViewModel
import com.kotl.jetpack.databinding.BindViewModelFactory
import com.kotl.jetpack.databinding.DatabindingActivity
import kotlinx.android.synthetic.main.activity_databinding_sample.*

class DatabindingSampleActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModels {
        providerLoginViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = DataBindingUtil.setContentView<ActivityDatabindingSampleBinding>(this, R.layout.activity_databinding_sample)
        binding.lifecycleOwner = this
        binding.viewModel = loginViewModel

        binding.BtnLogin.setOnClickListener(View.OnClickListener {
            val phone = loginViewModel.loginPhone.value
            val pwd = loginViewModel.loginPwd.value
            Toast.makeText(this, "phone:$phone,\npwd:$pwd", Toast.LENGTH_SHORT).show()
        })
    }

    fun providerLoginViewModelFactory(activity: DatabindingSampleActivity): LoginViewModelFactory {
        return LoginViewModelFactory(activity)
    }
}