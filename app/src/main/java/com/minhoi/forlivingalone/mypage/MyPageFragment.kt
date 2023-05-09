package com.minhoi.forlivingalone.mypage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.minhoi.forlivingalone.R
import com.minhoi.forlivingalone.databinding.FragmentMyPageBinding
import com.minhoi.forlivingalone.login.LoginActivity


class MyPageFragment : Fragment() {

    private lateinit var binding : FragmentMyPageBinding
    private lateinit var viewModel : MyPageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val auth = FirebaseAuth.getInstance()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_page, container, false )
        viewModel = ViewModelProvider(this).get(MyPageViewModel::class.java)

        binding.homeLogo.setOnClickListener {
            it.findNavController().navigate(R.id.action_myPageFragment_to_homeFragment)

        }

        binding.logOutBtn.setOnClickListener {
            auth.signOut()
            startActivity(Intent(context, LoginActivity::class.java))
        }

        binding.nickNameChange.setOnClickListener {
            // getActivity()가 null을 반환할 수 있음. 추후 수정 예정.
            val intent = Intent(activity, NickNameChangeActivity::class.java)
            startActivity(intent)
        }

        viewModel.getUserName { user ->
            binding.userName.text = user.name
            binding.userNickname.text = user.nickname
        }

        return binding.root
    }




}