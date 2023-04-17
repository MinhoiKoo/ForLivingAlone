package com.minhoi.forlivingalone.login

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.minhoi.forlivingalone.User

class LoginRepository {

    private val auth = FirebaseAuth.getInstance()
    private var isLoginSucceed : Boolean = false

//    fun getUserName(callback: (User) -> Unit) {
//        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                val user = dataSnapshot.getValue(User::class.java)!!
//                callback(user)
//            }

    fun login(email: String, password: String, callback : (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                val isLoginSucceed = task.isSuccessful
                callback(isLoginSucceed)
            }
    }
}
