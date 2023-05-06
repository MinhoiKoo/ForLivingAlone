package com.minhoi.forlivingalone.utils

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class Ref {

    companion object {
        val database = Firebase.database
        val boardRef = database.getReference("board")

    }

    fun getTime() : String {
        val currentDateTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA).format(currentDateTime)

        return dateFormat
    }

    fun getDate() : String {
        val currentDateTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("MM.dd", Locale.KOREA).format(currentDateTime)

        return dateFormat
    }
}