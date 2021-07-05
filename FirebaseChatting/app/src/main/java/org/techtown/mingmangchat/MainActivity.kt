package org.techtown.mingmangchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import org.techtown.mingmangchat.Adapter.ChatAdapter
import org.techtown.mingmangchat.data.ChatData

class MainActivity : AppCompatActivity() {
    private lateinit var chat_adapter : ChatAdapter
    private val NICKNAME = "NICK1"
    private lateinit var database :FirebaseDatabase
    private lateinit var myRef : DatabaseReference
    private lateinit var childListner : ChildEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRcv()

        // Write a message to the database
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference("message")


        button.setOnClickListener {
            //데이터 넣는 방식
            var message = editText.text.toString()
            var chat = ChatData(NICKNAME, message, NICKNAME,"","")
            myRef.push().setValue(chat)
            editText.setText("")
        }

//                var chat = p0.getValue(ChatData::class.java)
//                chat_adapter.addItem(ChatData(chat?.id, chat?.message, chat?.user
//                            ,chat?.profile, chat?.time))
        val childListener = object : ChildEventListener{
            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                var chat = p0.getValue(ChatData::class.java)
                chat_adapter.addItem(chat)
                recyclerView.scrollToPosition(recyclerView.adapter!!.itemCount - 1)
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                TODO("Not yet implemented")
            }
        }

        myRef.addChildEventListener(childListener)

    }





    private fun initRcv(){
        chat_adapter = ChatAdapter(this, NICKNAME)

        recyclerView.apply {
            adapter = chat_adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        myRef.removeEventListener(childListner)
    }
}
