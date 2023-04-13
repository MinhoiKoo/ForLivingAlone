package com.minhoi.forlivingalone

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MyPageViewModel : ViewModel() {
    private val database = FirebaseDatabase.getInstance()
    private val auth = FirebaseAuth.getInstance()
    fun getUserName() : User {
        var user = User()
        val postListener = object : ValueEventListener {
            // boomark_list의 내용이 변경될 때 마다 실행되는 함수.
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                user = dataSnapshot.getValue(User::class.java)!!
                Log.d("userinData", user.toString())
            }
            override fun onCancelled(error: DatabaseError) {
            }
        }
        database.getReference("users").child(auth.currentUser!!.uid).addValueEventListener(postListener)
        return user
    }

}