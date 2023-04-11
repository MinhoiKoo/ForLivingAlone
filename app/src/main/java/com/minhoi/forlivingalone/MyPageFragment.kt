package com.minhoi.forlivingalone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.minhoi.forlivingalone.databinding.FragmentMyPageBinding
import com.minhoi.forlivingalone.login.LoginActivity


class MyPageFragment : Fragment() {

    private lateinit var binding : FragmentMyPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val auth = FirebaseAuth.getInstance()
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_my_page, container, false )

        binding.homeLogo.setOnClickListener {
            it.findNavController().navigate(R.id.action_myPageFragment_to_homeFragment)

        }

        binding.logOutBtn.setOnClickListener {
            auth.signOut()
            startActivity(Intent(context, LoginActivity::class.java))
        }
        return binding.root
    }


}