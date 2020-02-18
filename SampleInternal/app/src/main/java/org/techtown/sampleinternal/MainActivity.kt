package org.techtown.sampleinternal

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.yongbeam.y_photopicker.util.photopicker.PhotoPickerActivity
import com.yongbeam.y_photopicker.util.photopicker.utils.YPhotoPickerIntent
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var imgAdapter : ImgAdp
    private val REQUEST_CODE = 95

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_internal.setOnClickListener {
            val inputData = edit_input.text.toString()
            val fos : FileOutputStream
            try{
                fos = openFileOutput("sample.txt", Context.MODE_PRIVATE)
                fos.write(inputData.toByteArray())
                Toast.makeText(this,"저장 완료",Toast.LENGTH_LONG).show()
                edit_input.text.clear()
                fos.close()
            }
            catch (e : Exception) {
                e.printStackTrace()
            }
        }

        bt_print.setOnClickListener {
            var fis : FileInputStream? = null
            var data : String? = null

            try{
                fis = openFileInput("sample.txt")
                val inputReader : InputStreamReader = InputStreamReader(fis)
                val bufferReader : BufferedReader = BufferedReader(inputReader)
                val stringBuilder : StringBuilder = StringBuilder()

                data = bufferReader.readLine()
                while(data != null){
                    stringBuilder.append(data)
                    data = bufferReader.readLine()
                }
                tv_output.text = stringBuilder.toString()
                fis.close()
            }
            catch (e : Exception){
                e.printStackTrace()
            }
        }

        bt_external.setOnClickListener {
            if (text_input.visibility == View.VISIBLE){
                edit_input.visibility = View.VISIBLE
                text_input.visibility = View.GONE
            }
            else{
                edit_input.visibility = View.GONE
                text_input.visibility = View.VISIBLE
                text_input.text = edit_input.text.toString()
            }
        }

        bt_img.setOnClickListener {
            val intent : YPhotoPickerIntent = YPhotoPickerIntent(this)
            intent.let {
                it.setMaxSelectCount(5)
                it.setShowCamera(true)
                it.setSelectCheckBox(false)
            }

            startActivityForResult(intent, 95)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var photos = mutableListOf<String>()

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS)

                initRcv(photos)
            }
        }
    }

    fun initRcv(photos : MutableList<String>){
        imgAdapter =  ImgAdp(this)
        rcv_img.adapter = imgAdapter
        rcv_img.layoutManager = LinearLayoutManager(this)

        imgAdapter.data = photos
        imgAdapter.notifyDataSetChanged()
    }


}
