package com.minhoi.forlivingalone.join

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class JoinViewModel : ViewModel() {
    private var auth = FirebaseAuth.getInstance()
    val email : MutableLiveData<String> = MutableLiveData("")
    val password : MutableLiveData<String> = MutableLiveData("")
    val passwordCheck : MutableLiveData<String> = MutableLiveData("")

    private var _isJoinBtnClicked : MutableLiveData<Boolean> = MutableLiveData(false)
    val isJoinBtnClicked : LiveData<Boolean>
        get() = _isJoinBtnClicked

    fun joinBtnClicked() {
        Log.d("Join", email.value.toString() + password.value.toString())
        auth.createUserWithEmailAndPassword(email.value.toString(), password.value.toString())
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    _isJoinBtnClicked.value = true
                } else {

                }
            }

    }

    fun isCorrect() {
        // id, pw, pwcheck 같은지 인증.
    }

}