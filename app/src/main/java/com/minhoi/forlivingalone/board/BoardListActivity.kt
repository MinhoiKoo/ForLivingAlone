package com.minhoi.forlivingalone.board

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.minhoi.forlivingalone.R
import com.minhoi.forlivingalone.databinding.ActivityBoardListBinding
import com.minhoi.forlivingalone.utils.Ref.Companion.boardRef

class BoardListActivity : AppCompatActivity() {

    private val boardContentList = arrayListOf<BoardContent>()
    private val rvAdapter = BoardAdapter(boardContentList)

    private lateinit var binding : ActivityBoardListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_board_list)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_list)

        binding.boardWrite.setOnClickListener {
            startActivity(Intent(this, BoardWriteActivity::class.java))
        }

        boardListRead()

        rvAdapter.setItemClickListener(object : BoardAdapter.OnItemClickListener {
            override fun onClick(v: View, psotion: Int) {
                Log.d("recycler", "clicked!!")
            }
        })
        val rv = binding.boardListRv
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(this)


    }

    private fun boardListRead() {
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
    }
}