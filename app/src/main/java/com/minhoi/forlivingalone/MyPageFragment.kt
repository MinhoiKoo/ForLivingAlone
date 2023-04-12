package com.minhoi.forlivingalone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.minhoi.forlivingalone.databinding.FragmentMyPageBinding
import com.minhoi.forlivingalone.login.LoginActivity


class MyPageFragment : Fragment() {
    private val database = FirebaseDatabase.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private lateinit var binding : FragmentMyPageBinding
    private lateinit var viewModel : MyPageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getUserName()
        val auth = FirebaseAuth.getInstance()
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_my_page, container, false )
        viewModel = ViewModelProvider(this).get(MyPageViewModel::class.java)

        binding.homeLogo.setOnClickListener {
            it.findNavController().navigate(R.id.action_myPageFragment_to_homeFragment)

        }

        binding.logOutBtn.setOnClickListener {
            auth.signOut()
            startActivity(Intent(context, LoginActivity::class.java))
        }


        return binding.root
    }

    private fun getUserName() {
        val postListener = object : ValueEventListener {
            // boomark_list의 내용이 변경될 때 마다 실행되는 함수.
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)!!
                binding.userName.text = user.name
                binding.userNickname.text = user.nickname
                Log.d("userinData", user.toString())
            }
            override fun onCancelled(error: DatabaseError) {
            }
        }
        database.getReference("users").child(auth.currentUser!!.uid).addValueEventListener(postListener)
    }


}