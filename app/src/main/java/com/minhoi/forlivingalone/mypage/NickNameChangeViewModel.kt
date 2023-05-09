package com.minhoi.forlivingalone.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.minhoi.forlivingalone.utils.Ref

class NickNameChangeViewModel : ViewModel() {

    private val currentUserRef = Ref.userRef.child(Ref.auth.currentUser?.uid.toString())
    val nickName = MutableLiveData<String>()


    var isValidNickName  = MutableLiveData(false)

    fun changeBtnClicked() {
        val map = hashMapOf<String,Any>()
        map.put("nickname", nickName.value.toString())
        currentUserRef.updateChildren(map)
        isValidNickName.value = true
    }





}