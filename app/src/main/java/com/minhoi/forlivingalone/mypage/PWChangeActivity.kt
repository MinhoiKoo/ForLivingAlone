package com.minhoi.forlivingalone.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.minhoi.forlivingalone.R
import com.minhoi.forlivingalone.databinding.ActivityPwchangeBinding
import com.minhoi.forlivingalone.utils.Ref

class PWChangeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPwchangeBinding
    private val user = Ref.auth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_pwchange)

        binding.changePasswordBtn.setOnClickListener {

            val userEmail = user?.email.toString()
            val currentPassword = binding.inputCurrentPassword.text.toString()
            val changePassword = binding.inputChangePassword.text.toString()
            val passwordCheck = binding.inputChangePasswordCheck.text.toString()

            // 계정 삭제, 기본 이메일 주소 설정 및 암호 변경과 같은 일부 보안에 민감한 작업을 수행하려면 사용자가 최근에 로그인해야 하기 때문에 사용자 재인증.
            // reauthenticateUser()은 비동기 작업이기 때문에 콜백으로 구현.

            reauthenticateUser(userEmail, currentPassword) { isValidEmailAndPw ->
                if (!isValidEmailAndPw) {
                    Toast.makeText(this, "이메일과 현재 비밀번호를 확인해주세요.", Toast.LENGTH_LONG).show()
                } else if (changePassword.length < 6) {
                    Toast.makeText(this, "비밀번호는 6자리 이상이어야 합니다.", Toast.LENGTH_LONG).show()
                } else if (changePassword != passwordCheck) {
                    Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show()
                } else {
                    user?.updatePassword(changePassword)
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // 비밀번호 변경 성공
                                finish()
                                Toast.makeText(this, "비밀번호 변경 성공", Toast.LENGTH_LONG).show()
                            } else {
                                // 비밀번호 변경 실패
                                Toast.makeText(this, "비밀번호 변경 실패. 앱 재접속 후 변경 바랍니다.", Toast.LENGTH_LONG).show()
                            }
                        }
                }
            }
        }
    }

    private fun reauthenticateUser(email: String, password: String, callback: (Boolean) -> Unit) {
        val credential: AuthCredential = EmailAuthProvider.getCredential(email, password)

        user?.reauthenticate(credential)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // 재인증 성공
                callback.invoke(true)
            } else {
                // 재인증 실패
                callback.invoke(false)
            }
        }
    }
}