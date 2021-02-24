package com.example.uip2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.uip2.model.songs

class AddSongsActivity : AppCompatActivity() {
    lateinit var addsong : Button
    lateinit var Titlea: EditText
    lateinit var Artista : EditText
    lateinit var Albuma : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_songs)
        val databaseHandler = SongsTableHandler(this)

        Titlea = findViewById(R.id.Titlea)
        Albuma = findViewById(R.id.Albuma)
        Artista = findViewById(R.id.Artista)

        addsong = findViewById(R.id.addbut)
        addsong.setOnClickListener{

            //form field

            val title = Titlea.text.toString()
            val artist = Artista.text.toString()
            val album = Albuma.text.toString()

            //assign
            val songs = songs(title, album, artist)

            //save
            databaseHandler.create(songs)
        }

    }
}