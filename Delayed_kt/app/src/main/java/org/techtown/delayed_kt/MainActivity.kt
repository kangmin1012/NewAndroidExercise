package org.techtown.delayed_kt

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            request()
        }
    }

    private fun request(){
        val title = "원격 요청"
        val message = "데이터를 요청하시겠습니까?"
        val titleButtonYes = "예"
        val titleButtonNo = "아니오"

        var dialog : AlertDialog = makeRequestDialog(title,message,titleButtonYes,titleButtonNo)
        dialog.show()

        textView.text = "대화상자 표시중..."
    }

    private fun makeRequestDialog(title : CharSequence, message : CharSequence,
                                  titleButtonYes : CharSequence, titleButtonNo : CharSequence) : AlertDialog{


        var requestDialog = AlertDialog.Builder(this)
        requestDialog.setTitle(title)
        requestDialog.setMessage(message)

        requestDialog.setPositiveButton(titleButtonYes, DialogInterface.OnClickListener { dialog, which ->
            textView.text = "5초 후에 결과 표시됨."

            handler.postDelayed(Runnable {
                textView.text="요청 완료됨."
            }, 5000)
        })

        requestDialog.setNegativeButton(titleButtonNo, DialogInterface.OnClickListener { dialog, which ->
        })

        return requestDialog.create()
    }
}
