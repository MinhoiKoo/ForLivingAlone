package com.minhoi.forlivingalone.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.minhoi.forlivingalone.User
import com.minhoi.forlivingalone.UserRepository

class MyPageViewModel : ViewModel() {
    private val userRepository = UserRepository()
    private val database = FirebaseDatabase.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val userRef = database.getReference("users").child(auth.currentUser!!.uid)

    private var _userName = MutableLiveData<String>()
    val userName : LiveData<String>
        get() = _userName

    private var _userNickName = MutableLiveData<String>()
    val userNickName : LiveData<String>
        get() = _userNickName

    init {
        userRepository.getUser(
            onSuccess = {
                _userName.postValue(it.name)
                _userNickName.postValue(it.nickname)
            },
            onError = {

            }
        )
    }
}