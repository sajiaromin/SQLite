package com.example.uip2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AddSongsActivity : AppCompatActivity() {
    lateinit var addsong : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_songs)

        addsong = findViewById(R.id.addbut)
        addsong.setOnClickListener{
            //form field

            //save
        }
    }
}