package org.techtown.chatting.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import org.techtown.chatting.R
import org.techtown.chatting.adapter.ChatAdapter
import org.techtown.chatting.data.ChatData
import java.net.URISyntaxException
import java.util.*

class MainActivity : AppCompatActivity() {
   companion object{
       private val BASE_URL = "https://reactsocketiomo.herokuapp.com/"

       private lateinit var socket : io.socket.client.Socket

       fun get() : io.socket.client.Socket {
           try{
               socket = IO.socket(BASE_URL)
           }catch (e : URISyntaxException){
               e.printStackTrace()
           }

           return socket
       }
   }

    private val chatAdapter by lazy { ChatAdapter(this) }
    private var dataList = arrayListOf<ChatData>()

    private var nickName = ""

    lateinit var  socket : Socket
    private lateinit var imm : InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nickName = "강민구"
        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        settingSocket()
        settingChatUi()
    }

    private fun settingSocket(){
        socket = get()
        socket.connect()
        socket.on("chat-msg",onMessageReceived)
    }

    private fun settingChatUi(){
        chatAdapter.apply {
            data = dataList
        }
        recyclerView.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = chatAdapter

        }

        button.setOnClickListener {
            val message : String = editText.text.toString()
//            메세지를 담는 JSON객체
            val userMessage = JSONObject()

            userMessage.put("name",nickName)
            userMessage.put("message",message)
//            emit()함수를 통해 데이터를 서버에 전달
            socket.emit("chat-msg",userMessage)
//            recylerView에 item 추가
            chatAdapter.addItem(ChatData("me", message, nickName,"",""))
            recyclerView.scrollToPosition(recyclerView.adapter!!.itemCount - 1)
//            전송이 끝나면 작성창 초기화
            editText.setText("")
        }

    }

    private val onMessageReceived = Emitter.Listener {
//        서버에서 받아온 데이터(it)를 JSON객체로 변환
        val receiveMessage = it[0] as JSONObject

        val tt = object : TimerTask(){
            override fun run() {
//                UI Update를 위한 스레드
                runOnUiThread {
                    if(receiveMessage.getString("name").toString() != nickName){
                        chatAdapter.addItem(ChatData("you",receiveMessage.getString("message").toString()
                        ,receiveMessage.getString("name").toString(),"",""))
                        chatAdapter.notifyDataSetChanged()
                        recyclerView.scrollToPosition(recyclerView.adapter!!.itemCount-1)
                    }
                }
            }
        }

        tt.run()
    }

}
