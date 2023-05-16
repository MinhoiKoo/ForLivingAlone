package com.minhoi.forlivingalone.board

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.minhoi.forlivingalone.R
import com.minhoi.forlivingalone.User
import com.minhoi.forlivingalone.UserRepository
import com.minhoi.forlivingalone.databinding.ActivityBoardContentBinding
import com.minhoi.forlivingalone.utils.Ref

class BoardContentActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBoardContentBinding
    private lateinit var boardKey : String
    private lateinit var commentList : MutableList<CommentData>
    private lateinit var commentKeyList : MutableList<String>
    private lateinit var commentListAdapter: CommentListAdapter
    private lateinit var userRepository: UserRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userRepository = UserRepository()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_content)

        val intent = getIntent()
        boardKey = intent.getStringExtra("key").toString()

        binding.boardDialogBtn.setOnClickListener {
            showDialog()
        }

        binding.commentWriteBtn.setOnClickListener {
            addComment()
            binding.commentWriteArea.setText("")
        }


        commentList = mutableListOf()
        commentKeyList = mutableListOf()

        getBoardData()
        getComment()

        commentListAdapter = CommentListAdapter(commentList, commentKeyList)
        binding.commentListView.adapter = commentListAdapter


    }

    private fun getBoardData() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val boardData = dataSnapshot.getValue(BoardContent::class.java)
                if(boardData != null) {
                    binding.boardTitle.text = boardData.title
                    binding.boardContent.text = boardData.content
                    binding.boardWritedTime.text = boardData.time

                    val userUid = Ref.auth.currentUser?.uid.toString()
                    val writedUserUid = boardData.uid
                    if(userUid.equals(writedUserUid)) {
                        binding.boardDialogBtn.isVisible = true
                    }
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
            val intent = Intent(this, BoardEditActivity::class.java)
            intent.putExtra("boardKey", boardKey)
            startActivity(intent)
            alertDialog.dismiss()

        }

        alertDialog.findViewById<Button>(R.id.removeBtn)?.setOnClickListener {

            //삭제하는지 한번 더 확인 Dialog 추가 예정
            Ref.boardRef.child(boardKey).removeValue()
            finish()

        }

    }

    private fun addComment() {
        val ref = Ref()
        val comment = binding.commentWriteArea.text.toString()
        val time = ref.getTime()
        val uid = Ref.auth.currentUser?.uid.toString()
        Ref.commentRef.child(boardKey).push().setValue(CommentData("",comment, time, uid, boardKey))
    }

    private fun getComment() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                commentList.clear()
                commentKeyList.clear()
                for (dataModel in dataSnapshot.children) {
                    val commentData = dataModel.getValue(CommentData::class.java)
                    if (commentData != null) {
                        commentList.add(commentData)
                        commentKeyList.add(dataModel.key.toString())
                        Log.d("comment", commentData.content)
                    }
                }
                commentListAdapter.notifyDataSetChanged()
            }
            override fun onCancelled(databaseError: DatabaseError) {

            }
        }
        if (boardKey != null) {
            Ref.commentRef.child(boardKey).addValueEventListener(postListener)
        }
//        Ref.commentRef.child(boardKey).addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                commentList.clear()
//                for (dataModel in snapshot.children) {
//                    val commentData = dataModel.getValue(CommentData::class.java)
//                    if (commentData != null) {
//                        commentList.add(commentData)
//                        Log.d("comment", commentData.content)
//                    }
//                }
//                commentListAdapter.notifyDataSetChanged()
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//        })
    }
}