package com.minhoi.forlivingalone

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.minhoi.forlivingalone.databinding.FragmentMyPageBinding


class MyPageFragment : Fragment() {

    private lateinit var binding : FragmentMyPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_my_page, container, false )

        binding.homeLogo.setOnClickListener {
            it.findNavController().navigate(R.id.action_myPageFragment_to_homeFragment)

        }
        return binding.root
    }


}