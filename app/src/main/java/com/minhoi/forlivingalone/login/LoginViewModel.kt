package com.minhoi.forlivingalone.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel() : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    var email : MutableLiveData<String> = MutableLiveData("")
    var password : MutableLiveData<String> = MutableLiveData("")

    private var _isloginBtnClicked : MutableLiveData<Boolean> = MutableLiveData(false)
    val isloginBtnClicked : LiveData<Boolean>
        get() = _isloginBtnClicked

    private var _isjoinBtnClicked : MutableLiveData<Boolean> = MutableLiveData(false)
    val isjoinBtnClicked : LiveData<Boolean>
        get() = _isjoinBtnClicked

    fun loginBtnClicked() {
        auth.signInWithEmailAndPassword(email.value.toString(), password.value.toString())
            .addOnCompleteListener{
                if (it.isSuccessful) {
                    _isloginBtnClicked.value = true
                } else {

                }
            }

    }

    fun joinBtnClicked() {
        _isjoinBtnClicked.value = true
    }


}