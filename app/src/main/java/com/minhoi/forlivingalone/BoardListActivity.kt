package com.minhoi.forlivingalone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.minhoi.forlivingalone.databinding.ActivityBoardListBinding

class BoardListActivity : AppCompatActivity() {

    override fun onDestroy() {
        super.onDestroy()
        Log.d("board", "onDestroy()")

    }

    private lateinit var binding : ActivityBoardListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_board_list)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_list)

        // 임시 데이터 저장. 추후 DB에서 가져온 내용을 리사이클러뷰에 뿌릴 예정.
        val boardData = arrayListOf<BoardData>()
        boardData.add(BoardData("Welcome to community", "Hi! i'm Minhoi Koo"))
        boardData.add(BoardData("Hello !", "Hi! i'm jjh"))
        boardData.add(BoardData("Hello !!", "Hi! i'm kdh"))
        boardData.add(BoardData("Hello !!!", "Hi! i'm ysh"))

        val rv = binding.boardListRv
        rv.adapter = BoardAdapter(boardData)
        rv.layoutManager = LinearLayoutManager(this)

    }
}