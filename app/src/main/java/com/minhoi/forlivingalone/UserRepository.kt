package com.minhoi.forlivingalone

import android.app.Application
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.minhoi.forlivingalone.utils.Ref

class UserRepository() {

    private val auth = Ref.auth
    private val currentUserUid = auth.currentUser?.uid.toString()
    private val userRef = Ref.userRef


    // 현재 로그인되어있는 사용자의 uid를 가져와서 DB 접근
    fun getUser(onSuccess: (User) -> Unit, onError: (DatabaseError) -> Unit) {

        userRef.child(currentUserUid).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                if (user != null) {
                    onSuccess(user)
                } else {

                }
            }

            override fun onCancelled(error: DatabaseError) {
                onError(error)
            }
        })
    }
}