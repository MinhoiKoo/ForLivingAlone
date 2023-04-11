package com.minhoi.forlivingalone.join

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.minhoi.forlivingalone.User

class JoinViewModel : ViewModel() {
    private var auth = FirebaseAuth.getInstance()
    private var database = Firebase.database.reference

    val email : MutableLiveData<String> = MutableLiveData("")
    val password : MutableLiveData<String> = MutableLiveData("")
    val passwordCheck : MutableLiveData<String> = MutableLiveData("")
    val name : MutableLiveData<String> = MutableLiveData("")
    val nickName : MutableLiveData<String> = MutableLiveData("")

    private var _isJoinBtnClicked : MutableLiveData<Boolean> = MutableLiveData(false)
    val isJoinBtnClicked : LiveData<Boolean>
        get() = _isJoinBtnClicked

    private var _isInputNameBtnClicked : MutableLiveData<Boolean> = MutableLiveData(false)
    val isInputNameBtnClicked : LiveData<Boolean>
        get() = _isInputNameBtnClicked

    fun joinBtnClicked() {
        Log.d("Join", email.value.toString() + password.value.toString())
        auth.createUserWithEmailAndPassword(email.value.toString(), password.value.toString())
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    auth.currentUser?.let { Log.d("Join", it.uid) }
                    _isJoinBtnClicked.value = true
                } else {

                }
            }
    }

    fun inputNameCompleteClicked() {
        // 중복된 닉네임이 아니면 DB에 저장 후 가입 완료.
        if(isValidNickName()) {
            val user = User(name.value.toString(), nickName.value.toString())
            database.child("users").child(auth.currentUser!!.uid).setValue(user)
            _isInputNameBtnClicked.value = true
        }
    }

    //닉네임 중복 검사
    private fun isValidNickName() : Boolean {
        // DB에서 탐색해서 닉네임이 같은게 있으면
        return true
    }

    fun isCorrect() {
        // id, pw, pwcheck 같은지 인증.
    }

}