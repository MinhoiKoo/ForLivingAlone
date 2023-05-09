package com.minhoi.forlivingalone.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.minhoi.forlivingalone.utils.Ref

class NickNameChangeViewModel : ViewModel() {

    private val _nickName = MutableLiveData<String>()
    val nickName : LiveData<String>
        get() = _nickName

    private val currentUserUid = Ref.auth.currentUser?.uid
    private val currentNickName = currentUserUid.get


    fun clickChangeNickBtn() : Int {
        if(currentNickName.equals(_nickName)) {
            return 2
        }
        return 1
    }

}