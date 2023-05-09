package com.minhoi.forlivingalone.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.minhoi.forlivingalone.R
import com.minhoi.forlivingalone.databinding.ActivityNickNameChangeBinding

class NickNameChangeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNickNameChangeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_nick_name_change)




    }
}