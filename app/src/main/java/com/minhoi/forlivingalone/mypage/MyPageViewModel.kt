package com.minhoi.forlivingalone.mypage

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.minhoi.forlivingalone.User

class MyPageViewModel : ViewModel() {
    private val database = FirebaseDatabase.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val userRef = database.getReference("users").child(auth.currentUser!!.uid)


    fun getUserName(callback: (User) -> Unit) {
        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)!!
                callback(user)
            }

            override fun onCancelled(error: DatabaseError) {
                // 오류 처리
            }
        })
    }


//        var user = User()
//        val postListener = object : ValueEventListener {
//            // boomark_list의 내용이 변경될 때 마다 실행되는 함수.
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                user = dataSnapshot.getValue(User::class.java)!!
//                Log.d("userinData", user.toString())
//            }
//            override fun onCancelled(error: DatabaseError) {
//            }
//        }
//        database.getReference("users").child(auth.currentUser!!.uid).addValueEventListener(postListener)
//        return user
    }
