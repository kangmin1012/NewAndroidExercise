package com.example.sampledaum

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

@Suppress("DEPRECATION")
class SampleFirebaseMessagingService : FirebaseMessagingService(){
    override fun onMessageReceived(p0: RemoteMessage) {
        if(p0.notification != null){
            sendNotification(p0.notification?.title, p0.notification?.body!!)
        }
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d("firebase","firebaseInstaceId : $p0")
    }

    private fun sendNotification(title : String?, body : String){
        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        //penddingIntent 를 Notificatiton에 전달하여 액티비티 실행
        val pendingIntent = PendingIntent.getActivity(this,0, intent, PendingIntent.FLAG_ONE_SHOT)

        // 알림 시 소리
        val defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notiBuilder : NotificationCompat.Builder

        // Service로 부터 notificationManager 호출
        val notiManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 안드로이드 하위 버전 호환 (오레오 버전 이하)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channelId = "pushing"
            val name = "Sample pushing"
            val description = "Channel for SamplePushing"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            notiManager.createNotificationChannel(channel)
            notiBuilder = NotificationCompat.Builder(this, channelId)
        }else{
            notiBuilder = NotificationCompat.Builder(this)
        }

        notiBuilder.setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(body)
            .setSound(defaultSound)
            .setContentIntent(pendingIntent)
        notiManager.notify(0, notiBuilder.build())



   }

}