package com.minhoi.forlivingalone.board

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.minhoi.forlivingalone.R
import com.minhoi.forlivingalone.databinding.ActivityBoardListBinding
import com.minhoi.forlivingalone.utils.Ref

class BoardListActivity : AppCompatActivity() {

    override fun onDestroy() {
        super.onDestroy()
        Log.d("board", "onDestroy()")

    }

    private lateinit var binding : ActivityBoardListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_board_list)


        val boardRef = Ref.boardRef
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_list)

        binding.boardWrite.setOnClickListener {
            startActivity(Intent(this, BoardWriteActivity::class.java))
        }

        val boardContentList = arrayListOf<BoardContent>()

        val rvAdapter = BoardAdapter(boardContentList)

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                boardContentList.clear()
                for (postSnapshot in dataSnapshot.children) {
                    val boardData = postSnapshot.getValue(BoardContent::class.java)
                    if (boardData != null) {
                        boardContentList.add(BoardContent(boardData.title, boardData.content))
                    }
                }
                rvAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }
        boardRef.addValueEventListener(postListener)

        val rv = binding.boardListRv
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(this)

    }
}