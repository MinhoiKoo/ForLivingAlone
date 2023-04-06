package com.minhoi.forlivingalone.join

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.minhoi.forlivingalone.R
import com.minhoi.forlivingalone.databinding.ActivityJoinBinding
import com.minhoi.forlivingalone.login.LoginActivity

class JoinActivity : AppCompatActivity() {
    private lateinit var joinViewModel: JoinViewModel
    private lateinit var binding : ActivityJoinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        joinViewModel = ViewModelProvider(this).get(JoinViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_join)
        binding.joinvm = joinViewModel

        binding.JoinBtn.setOnClickListener {
            joinViewModel.joinBtnClicked()
        }

        setObserve()

    }
    private fun setObserve() {
        joinViewModel.isJoinBtnClicked.observe(this) {
            if(it) {
                finish()
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
    }
}