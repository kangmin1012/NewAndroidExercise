package org.techtown.handler_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            var thread = BackgroundThread()
            thread.start()
        }
    }

    inner class BackgroundThread : Thread() {
        var value : Int = 0

        public override fun run() : Unit {
            for(i in 1 until 100){
                try{
                    Thread.sleep(1000)
                }catch (e : Exception){}

                value += 1
                Log.d("Thread","value : " + value)

                handler.post(Runnable {
                    textView.text = "value 값 : ${value}"
                })
            }
        }
    }
}
