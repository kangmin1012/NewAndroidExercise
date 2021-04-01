package com.example.samplekakaologin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.samplekakaologin.databinding.ActivityMainBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient

class MainActivity : AppCompatActivity() {

    private val TAG = "카카오 로그인"

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var kakaoLoginCallBack: (OAuthToken?, Throwable?) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        kakaoLoginCallBack = { token, error ->
            if (error != null) {
                Toast.makeText(this, "인증코드 요청 단계에서 실패", Toast.LENGTH_SHORT).show()
            } else if (token != null) {
                Toast.makeText(this, "인증코드 요청 성공", Toast.LENGTH_SHORT).show()
                againRequestUserAgreement()
            }
        }

        binding.btnKakaoLogin.setOnClickListener {
            loginKakao()
        }

        binding.btnKakaoLogout.setOnClickListener {
            logoutKaKao()
        }

        binding.btnKakaoReject.setOnClickListener {
            rejectKaKao()
        }

    }

    private fun loginKakao() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) { // 카카오톡으로 로그인
            UserApiClient.instance.loginWithKakaoTalk(this, callback = kakaoLoginCallBack)
        } else { // 카카오 계정으로 로그인
            UserApiClient.instance.loginWithKakaoAccount(this, callback = kakaoLoginCallBack)
        }
    }

    private fun logoutKaKao() {
        UserApiClient.instance.logout { error ->
            if (error != null) {
                Toast.makeText(this, "로그아웃 실패. SDK에서 토큰 삭제", Toast.LENGTH_SHORT).show()
                Log.e(TAG, "로그아웃 실패. SDK에서 토큰 삭제", error)
            } else
                Toast.makeText(this, "로그아웃 성공", Toast.LENGTH_SHORT).show()
        }
    }

    private fun rejectKaKao() {
        UserApiClient.instance.unlink { error ->
            if (error != null) {
                Toast.makeText(this, "연결 끊기 실패", Toast.LENGTH_SHORT).show()
                Log.e(TAG, "연결 끊기 실패", error)
            } else
                Toast.makeText(this, "연결 끊기 성공", Toast.LENGTH_SHORT).show()
        }
    }

    private fun accessToken() {
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                Toast.makeText(this, "토큰 정보 보기 실패", Toast.LENGTH_SHORT).show()
                Log.e(TAG, "토큰 정보 보기 실패", error)
            } else if (tokenInfo != null) {
                Log.i(
                    TAG, "토큰 정보 보기 성공" +
                            "\n회원번호: ${tokenInfo.id}" +
                            "\n만료시간: ${tokenInfo.expiresIn} 초"
                )
                requestKaKaoUserInfo()
            }
        }
    }

    private fun requestKaKaoUserInfo() {
        UserApiClient.instance.me { user, error ->
            if (error != null)
                Log.e(TAG, "사용자 정보 요청 실패", error)
            else if (user != null) {
                Log.e(TAG, "사용자 정보 요청  성공")
                binding.txtId.text = user.kakaoAccount?.profile?.nickname
                Glide.with(this)
                    .load(user.kakaoAccount?.profile?.thumbnailImageUrl)
                    .into(binding.imgProfile)

                Log.i(TAG, " 유저 아이디 : ${user.id}")
                Log.i(TAG, """사용자 추가 정보
                    | 성별 : ${user.kakaoAccount?.gender}
                    | 연령대 : ${user.kakaoAccount?.ageRange}
                    | 생일 : ${user.kakaoAccount?.birthday}
                """.trimMargin())

            }

        }
    }

    private fun againRequestUserAgreement() {
        UserApiClient.instance.me { user, error ->
            if (error != null)
                Log.e(TAG, "사용자 정보 재 요청 실패", error)
            else if (user != null) {
                val scopes = mutableListOf<String>()

                if (user.kakaoAccount?.genderNeedsAgreement == true)
                    scopes.add("gender")
                if (user.kakaoAccount?.ageRangeNeedsAgreement == true)
                    scopes.add("age_range")
                if (user.kakaoAccount?.birthdayNeedsAgreement == true)
                    scopes.add("birthday")

                if (scopes.size > 0) {
                    Log.d(TAG, "사용자 추가 동의 필요")

                    UserApiClient.instance.loginWithNewScopes(this, scopes) { token, error ->
                        if (error != null)
                            Log.e(TAG, "사용자 추가 동의 실패", error)
                        else {
                            Log.d(TAG, "allowed scopes: ${token!!.scopes}")
                            requestKaKaoUserInfo()
                        }
                    }
                }
                else
                    requestKaKaoUserInfo()
            }
        }

    }
}