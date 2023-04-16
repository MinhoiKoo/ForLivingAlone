package com.minhoi.forlivingalone.join

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.minhoi.forlivingalone.R
import com.minhoi.forlivingalone.databinding.FragmentJoinInputIdPwBinding
import com.minhoi.forlivingalone.login.LoginActivity

class JoinInputIdPwFragment : Fragment() {

    private lateinit var joinViewModel: JoinViewModel
    private lateinit var binding : FragmentJoinInputIdPwBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_join_input_id_pw, container, false)
        val view = binding.root
//        joinViewModel = ViewModelProvider(this).get(JoinViewModel::class.java)
        joinViewModel = ViewModelProvider(this).get(JoinViewModel::class.java)

        binding.joinvm = joinViewModel

        binding.JoinBtn.setOnClickListener {
            joinViewModel.joinBtnClicked()
        }

        joinViewModel.isJoinBtnClicked.observe(viewLifecycleOwner) {
            if (it) {
                view.findNavController().navigate(R.id.action_joinInputIdPwFragment_to_joinInputNicknameFragment)
            }
        }
        return view
    }

}