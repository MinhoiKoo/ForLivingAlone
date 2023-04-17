package com.minhoi.forlivingalone

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.minhoi.forlivingalone.board.BoardAdapter
import com.minhoi.forlivingalone.board.BoardData
import com.minhoi.forlivingalone.board.BoardListActivity
import com.minhoi.forlivingalone.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.myPageImg.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_myPageFragment)
        }

        binding.boardPlusBtn.setOnClickListener {
            startActivity(Intent(activity, BoardListActivity::class.java))
        }
        // 임시 데이터 저장. 추후 DB에서 가져온 내용을 리사이클러뷰에 뿌릴 예정.
        val boardData = arrayListOf<BoardData>()
        boardData.add(BoardData("Welcome to community", "Hi! i'm Minhoi Koo"))
        boardData.add(BoardData("Hello !", "Hi! i'm jjh"))
        boardData.add(BoardData("Hello !!", "Hi! i'm kdh"))
        boardData.add(BoardData("Hello !!!", "Hi! i'm ysh"))

        binding.boardRv.adapter = BoardAdapter(boardData)
        binding.boardRv.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

}