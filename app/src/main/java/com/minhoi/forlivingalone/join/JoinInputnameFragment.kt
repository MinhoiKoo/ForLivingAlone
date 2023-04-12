package com.minhoi.forlivingalone.join

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.minhoi.forlivingalone.MainActivity
import com.minhoi.forlivingalone.R
import com.minhoi.forlivingalone.databinding.FragmentJoinInputNameBinding


class JoinInputnameFragment : Fragment() {

    val args : JoinInputnameFragmentArgs by navArgs()
    private lateinit var binding : FragmentJoinInputNameBinding
    private lateinit var viewModel : JoinViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_join_input_name, container, false)
        val view = binding.root


        viewModel = ViewModelProvider(this).get(JoinViewModel::class.java)
        binding.joinvm = viewModel

        viewModel.email
        binding.btnName.setOnClickListener {
            viewModel.inputNameCompleteClicked()
        }

        viewModel.isInputNameBtnClicked.observe(viewLifecycleOwner){
            if(it) {
                //닉네임, 이름 설정 완료.
                startActivity(Intent(getActivity(), MainActivity::class.java))

            }
        }




        return view
    }

}