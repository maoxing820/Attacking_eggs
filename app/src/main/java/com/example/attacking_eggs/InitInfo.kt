package com.example.attacking_eggs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class InitInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init_info)
        intent.getStringExtra("phoneNumber").also {

        }
    }
}