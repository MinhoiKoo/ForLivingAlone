package com.minhoi.forlivingalone.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.minhoi.forlivingalone.R
import com.minhoi.forlivingalone.databinding.ActivityBoardWriteBinding
import com.minhoi.forlivingalone.utils.Ref

class BoardWriteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBoardWriteBinding
    private lateinit var database : DatabaseReference
    private lateinit var ref : Ref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Firebase.database.getReference("board")

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_write)

        ref = Ref()
        binding.boardWriteExitBtn.setOnClickListener {
            finish()
        }

        binding.boardWriteCompleteBtn.setOnClickListener {
            val title = binding.boardWriteTitle.text.toString()
            val content = binding.boardWriteContent.text.toString()
            val time = ref.getTime()
            val date = ref.getDate()
            val uid = FirebaseAuth.getInstance().currentUser?.uid.toString()
            database.push().setValue(BoardContent(title, content, uid, time, date))
            finish()
        }


    }
}