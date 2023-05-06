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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.minhoi.forlivingalone.board.BoardAdapter
import com.minhoi.forlivingalone.board.BoardContent
import com.minhoi.forlivingalone.board.BoardListActivity
import com.minhoi.forlivingalone.databinding.FragmentHomeBinding
import com.minhoi.forlivingalone.utils.Ref
import com.minhoi.forlivingalone.utils.Ref.Companion.boardRef


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

        val boardContentList = arrayListOf<BoardContent>()

        val rvAdapter = BoardAdapter(boardContentList)

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var count = 0
                boardContentList.clear()
                for (postSnapshot in dataSnapshot.children) {
                    val boardData = postSnapshot.getValue(BoardContent::class.java)
                    if (boardData != null && count <=4) {
                        boardContentList.add(BoardContent(boardData.title, boardData.content))
                        count += 1
                    }
                }
                rvAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }
        boardRef.addValueEventListener(postListener)

        binding.boardRv.adapter = rvAdapter
        binding.boardRv.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

}