package com.minhoi.forlivingalone.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.minhoi.forlivingalone.R
import com.minhoi.forlivingalone.databinding.ActivityBoardWriteBinding

class BoardWriteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBoardWriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_write)

        binding.boardWriteExitBtn.setOnClickListener {
            finish()
        }


    }
}