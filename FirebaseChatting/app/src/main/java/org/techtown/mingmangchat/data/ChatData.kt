package org.techtown.mingmangchat.data

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ChatData(
    var id : String?="", // 닉네임
    var message : String?="", // 메세지
    var user : String?="", // 나인지 아닌지 판단
    var profile : String?="", // 프로필 사진
    var time : String?="" // 시간
)