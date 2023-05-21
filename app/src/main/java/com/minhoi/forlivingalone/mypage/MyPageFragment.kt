package com.minhoi.forlivingalone.mypage

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.minhoi.forlivingalone.R
import com.minhoi.forlivingalone.databinding.FragmentMyPageBinding
import com.minhoi.forlivingalone.login.LoginActivity
import com.minhoi.forlivingalone.utils.Ref
import java.io.File


class MyPageFragment : Fragment() {

    private lateinit var binding : FragmentMyPageBinding
    private lateinit var viewModel : MyPageViewModel
    private lateinit var userNickNameObserver: Observer<String>
    private val storage = Firebase.storage
    private val userUid = Ref.auth.currentUser?.uid.toString()


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

        getImageData()
        binding.homeLogo.setOnClickListener {
            it.findNavController().navigate(R.id.action_myPageFragment_to_homeFragment)

        }

        binding.passWordChange.setOnClickListener {
            val intent = Intent(activity, PWChangeActivity::class.java)
            startActivity(intent)
        }

        binding.nickNameChange.setOnClickListener {
            // getActivity()가 null을 반환할 수 있음. 추후 수정 예정.
            val intent = Intent(activity, NickNameChangeActivity::class.java)
            startActivity(intent)
        }

        binding.imageChange.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(intent, 100)
        }

        binding.logOutBtn.setOnClickListener {
            auth.signOut()
            activity?.finish()
            startActivity(Intent(context, LoginActivity::class.java))
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

    private fun getImageData() {
        val storage = Firebase.storage.reference.child("images").child(userUid)

        val imageView = binding.userImage

        storage.downloadUrl.addOnCompleteListener { task ->
            if(task.isSuccessful) {
                Glide.with(this).load(task.result).into(imageView)
            }
        }
    }

    private fun uploadUserImage(key : Uri) {


        // Firebase Storage에 이미지 업로드
        val storageRef = FirebaseStorage.getInstance().reference
        val imagesRef = storageRef.child("images")

        //사용자의 uid값으로 이미지 이름 설정.
        val imageFileName = userUid
        val imageRef = imagesRef.child(imageFileName)

        val uploadTask = key?.let { imageRef.putFile(it) }

        uploadTask?.addOnSuccessListener {

        }?.addOnFailureListener { exception ->
            // 이미지 업로드 실패
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == 100) {
            val imageUri: Uri? = data?.data
            if (imageUri != null) {
                uploadUserImage(imageUri)
            }
        }
    }
}