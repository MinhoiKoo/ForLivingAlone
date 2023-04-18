package com.minhoi.forlivingalone.login

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.minhoi.forlivingalone.User

class LoginRepository {

    private val auth = FirebaseAuth.getInstance()

    fun login(email: String, password: String, callback : (Boolean) -> Unit) {
        Log.d("repository", email.toString() + password.toString())
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                val isLoginSucceed = task.isSuccessful
                callback(isLoginSucceed)
            }
    }
}
