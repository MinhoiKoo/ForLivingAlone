package com.minhoi.forlivingalone.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.minhoi.forlivingalone.R
import com.minhoi.forlivingalone.databinding.ActivityNickNameChangeBinding

class NickNameChangeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNickNameChangeBinding
    private lateinit var viewModel: NickNameChangeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_nick_name_change)
        viewModel = ViewModelProvider(this).get(NickNameChangeViewModel::class.java)
        binding.changeNickVM = viewModel

        binding.btnChangeNickName.setOnClickListener {
            viewModel.changeBtnClicked()
        }

        viewModel.isValidNickName.observe(this) {
            if(it) {
                finish()
                Toast.makeText(this, "닉네임 변경 완료", Toast.LENGTH_LONG).show()
            }
        }

    }
}