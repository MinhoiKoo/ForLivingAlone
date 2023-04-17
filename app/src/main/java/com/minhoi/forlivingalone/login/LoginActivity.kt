package com.minhoi.forlivingalone.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.minhoi.forlivingalone.MainActivity
import com.minhoi.forlivingalone.R
import com.minhoi.forlivingalone.databinding.ActivityLoginBinding
import com.minhoi.forlivingalone.join.JoinActivity

class LoginActivity() : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.loginvm = loginViewModel

        binding.loginBtn.setOnClickListener {
            loginViewModel.loginBtnClicked()
        }

        binding.JoinBtn.setOnClickListener {
            loginViewModel.joinBtnClicked()
        }


        setObserve()

    }
    private fun setObserve() {
        loginViewModel.isloginBtnClicked.observe(this){
            if(it) {
                finish()
                startActivity(Intent(this, MainActivity::class.java))
            }
            else {}
        }

        loginViewModel.isjoinBtnClicked.observe(this) {
            if(it) {
                startActivity(Intent(this, JoinActivity::class.java))
            }
        }
    }
}