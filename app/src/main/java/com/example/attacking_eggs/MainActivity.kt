package com.example.attacking_eggs

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    private val REQUEST_IMAGE_CODE = 123
    private val REQUEST_VIDIO_CODE = 345
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mHeader.setOnClickListener {
            // 从相册里面获取一张图片
            Intent().apply {
                action = Intent.ACTION_PICK
                type = "image/*"

                startActivityForResult(this,REQUEST_IMAGE_CODE)
            }
        }

        // 获取头像
        File(filesDir,"header.jpg").also {
            if(it.exists()){
                BitmapFactory.decodeFile(it.path).also { image ->
                    mHeader.setImageBitmap(image)
                }
            }
        }

        Register.setOnClickListener {
            Intent().apply {
                setClass(this@MainActivity, SignUpActivity::class.java)
                startActivity(this)
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REQUEST_IMAGE_CODE -> {
                // 图片 InputStream OutputStream Writer Reader
                // 判断用户是否取消操作
                if (resultCode != Activity.RESULT_CANCELED){
                    // 获取图片 uri
                    data?.data.apply {
                        // 对IO操作使用use
                        this?.let { contentResolver.openInputStream(it) }.use {
                            // Bitmap
                            BitmapFactory.decodeStream(it).also { image ->
                                // 显示图片
                                mHeader.setImageBitmap(image)
                                // 把图片缓存起来
                                val file = File(filesDir, "header.jpg")
                                FileOutputStream(file).also { fos ->
                                    // 将图片缓存到fos对应的路径中
                                    image.compress(Bitmap.CompressFormat.JPEG, 60, fos)
                                }
                            }
                        }
                    }
                }

            }

            REQUEST_VIDIO_CODE -> {

            }
        }
    }
}