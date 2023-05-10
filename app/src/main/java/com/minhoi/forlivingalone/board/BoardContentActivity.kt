package com.minhoi.forlivingalone.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.minhoi.forlivingalone.R
import com.minhoi.forlivingalone.databinding.ActivityBoardContentBinding
import com.minhoi.forlivingalone.utils.Ref

class BoardContentActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBoardContentBinding
    private lateinit var boardKey : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_content)

        val intent = getIntent()
        boardKey = intent.getStringExtra("key").toString()

        binding.boardDialogBtn.setOnClickListener {
            showDialog()
        }

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

    private fun showDialog() {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.board_dialog, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle("게시글 수정/삭제")

        val alertDialog = mBuilder.show()
        alertDialog.findViewById<Button>(R.id.editBtn)?.setOnClickListener {

        }

        alertDialog.findViewById<Button>(R.id.removeBtn)?.setOnClickListener {

            //삭제하는지 한번 더 확인 Dialog 추가 예정

            Ref.boardRef.child(boardKey).removeValue()
            finish()

        }

    }

}