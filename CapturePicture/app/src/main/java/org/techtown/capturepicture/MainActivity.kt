package org.techtown.capturepicture


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider

import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import com.gun0912.tedpermission.PermissionListener
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    val REQUEST_IMAGE_CAPTURE = 1
    lateinit var currentPhotoPath : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        settingPermission() // 권한체크 시작

        btn_picture.setOnClickListener {
            startCapture()
        }

    }

    fun settingPermission(){
        var permis = object  : PermissionListener{
//            어떠한 형식을 상속받는 익명 클래스의 객체를 생성하기 위해 다음과 같이 작성
            override fun onPermissionGranted() {
               Toast.makeText(this@MainActivity, "권한 허가", Toast.LENGTH_SHORT)
                   .show()
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                Toast.makeText(this@MainActivity, "권한 거부", Toast.LENGTH_SHORT)
                    .show()
                ActivityCompat.finishAffinity(this@MainActivity) // 권한 거부시 앱 종료
            }
        }

        TedPermission.with(this)
            .setPermissionListener(permis)
            .setRationaleMessage("카메라 사진 권한 필요")
            .setDeniedMessage("카메라 권한 요청 거부")
            .setPermissions(
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA)
            .check()
    }

    fun startCapture(){
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                val photoFile: File? = try{
                    createImageFile()
                }catch(ex:IOException){
                    null
                }
                photoFile?.also{
                    val photoURI : Uri = FileProvider.getUriForFile(
                        this,
                        "org.techtown.capturepicture.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile() : File{
        val timeStamp : String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir : File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply{
            currentPhotoPath = absolutePath
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK){
            val bitmap = BitmapFactory.decodeFile(currentPhotoPath)
            lateinit var exif : ExifInterface

            try{
                exif = ExifInterface(currentPhotoPath)
                var exifOrientation = 0
                var exifDegree = 0

                if (exif != null) {
                    exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                        ExifInterface.ORIENTATION_NORMAL)
                    exifDegree = exifOrientationToDegress(exifOrientation)
                }

                img_picture.setImageBitmap(rotate(bitmap, exifDegree))
            }catch (e : IOException){
                e.printStackTrace()
            }

        }
    }

    private fun exifOrientationToDegress(exifOrientation: Int): Int {
        when(exifOrientation){
            ExifInterface.ORIENTATION_ROTATE_90 ->{
                Log.d("rotate","rotate90")
                return 90
            }
            ExifInterface.ORIENTATION_ROTATE_180 -> {
                Log.d("rotate","rotate180")
                return 180
            }
            ExifInterface.ORIENTATION_ROTATE_270 ->{
                Log.d("rotate","rotate270")
                return 270
            }
            else -> {
                Log.d("rotate","rotate0")
                return 0
            }

        }
    }

    private fun rotate(bitmap: Bitmap, degree: Int) : Bitmap {
        Log.d("rotate","init rotate")
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix,true)
    }
}
