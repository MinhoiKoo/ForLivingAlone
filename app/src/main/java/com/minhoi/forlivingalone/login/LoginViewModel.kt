package com.minhoi.forlivingalone.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel() : ViewModel() {
    private val loginRepository = LoginRepository()
    val email : MutableLiveData<String> = MutableLiveData()
    val password : MutableLiveData<String> = MutableLiveData()

    private var _isloginBtnClicked : MutableLiveData<Boolean> = MutableLiveData(false)
    val isloginBtnClicked : LiveData<Boolean>
        get() = _isloginBtnClicked

    private var _isjoinBtnClicked : MutableLiveData<Boolean> = MutableLiveData(false)
    val isjoinBtnClicked : LiveData<Boolean>
        get() = _isjoinBtnClicked

    fun loginBtnClicked() {
        loginRepository.login(email.value.toString(), password.value.toString()) { result ->
            if (result) {
                _isloginBtnClicked.value = true
            }
        }
    }

    // 회원가입 버튼 누르면 회원가입 창으로 이동.
    fun joinBtnClicked() {
        _isjoinBtnClicked.value = true
    }

}