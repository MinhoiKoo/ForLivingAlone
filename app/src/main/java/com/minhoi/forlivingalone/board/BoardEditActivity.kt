package com.minhoi.forlivingalone.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.minhoi.forlivingalone.R
import com.minhoi.forlivingalone.databinding.ActivityBoardEditBinding
import com.minhoi.forlivingalone.utils.Ref

class BoardEditActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBoardEditBinding
    private lateinit var boardKey : String
    private lateinit var ref : Ref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_edit)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_edit)

        boardKey = intent.getStringExtra("boardKey").toString()

        ref = Ref()

        binding.boardEditCompleteBtn.setOnClickListener {
            editBoardData()
            finish()
        }

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val boardData = dataSnapshot.getValue(BoardContent::class.java)
                if(boardData != null) {
                    binding.boardEditTitle.text = boardData.title
                    binding.boardEditWriteContent.setText(boardData.content)
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {

            }
        }
        if (boardKey != null) {
            Ref.boardRef.child(boardKey).addValueEventListener(postListener)
        }

    }

    private fun editBoardData() {
        val title = binding.boardEditTitle.text.toString()
        val content = binding.boardEditWriteContent.text.toString()
        val time = ref.getTime()
        val date = ref.getDate()
        val uid = Ref.auth.currentUser?.uid.toString()
        Ref.boardRef.child(boardKey).setValue(BoardContent(title, content, uid, time, date))
    }
}