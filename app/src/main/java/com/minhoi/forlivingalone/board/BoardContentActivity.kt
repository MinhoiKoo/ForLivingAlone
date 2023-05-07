package com.minhoi.forlivingalone.board

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.minhoi.forlivingalone.R
import com.minhoi.forlivingalone.databinding.ActivityBoardContentBinding
import com.minhoi.forlivingalone.utils.Ref

class BoardContentActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBoardContentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_content)

        val intent = getIntent()
        val boardKey = intent.getStringExtra("key")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val boardData = dataSnapshot.getValue(BoardContent::class.java)
                if(boardData != null) {
                    binding.boardTitle.text = boardData.title
                    binding.boardContent.text = boardData.content
                    binding.boardWritedTime.text = boardData.time
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {

            }
        }
        if (boardKey != null) {
            Ref.boardRef.child(boardKey).addValueEventListener(postListener)
        }

    }

}