package com.minhoi.forlivingalone.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.minhoi.forlivingalone.R
import com.minhoi.forlivingalone.databinding.FragmentMyPageBinding
import com.minhoi.forlivingalone.login.LoginActivity
import com.minhoi.forlivingalone.utils.Ref


class MyPageFragment : Fragment() {

    private lateinit var binding : FragmentMyPageBinding
    private lateinit var viewModel : MyPageViewModel
    private lateinit var userNickNameObserver: Observer<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val auth = Ref.auth
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

        viewModel.userName.observe(viewLifecycleOwner) {
            binding.userName.text = it
        }
        userNickNameObserver = Observer {
            binding.userNickname.text = it
        }


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        Log.d("onResume", "onResume()")
        viewModel.userName.observe(viewLifecycleOwner) {
            binding.userName.text = it

        }
        viewModel.userNickName.observe(viewLifecycleOwner, userNickNameObserver)
        Log.d("name", viewModel.userNickName.value.toString())
    }

}