package com.example.uip2

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    val queuedList = ArrayList<String>()
    var songsArray: ArrayList<String> = ArrayList()

    lateinit var adapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val holder  = arrayOf(
            "Super Far", "ILYSB", "13",
            "Hericane", "Hurts", "Malibu Nights",
            "Thick and Thin", "I Don't Wanna Love You Anymore", "Thru These Tears",
            "If You See Her", "Heart Won't Let Me", "Good Guys",
            "Nobody Else", "Paper", "If This Is The Last Time"
        )
        for (string in holder){
            songsArray.add(string)
        }

        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsArray)
        val songsListView: ListView = findViewById<ListView>(R.id.songsListView)
        songsListView.adapter = adapter
        registerForContextMenu(songsListView)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater =menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        return when(item.itemId){
            R.id.go_to_songs_act -> {
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            R.id.go_to_albums_act -> {
                startActivity(Intent(this, AlbumsActivity::class.java))
                true
            }
            R.id.add_to_queue_act -> {
                val intent = Intent(this, QueuedSongsActivity::class.java)
                intent.putStringArrayListExtra("queuedList", queuedList)
                startActivity(intent)
                true
            }
            R.id.add_act -> {
                val intent = Intent(this, AddSongsActivity::class.java )
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.song_item_menu, menu)
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {


        return when (item.itemId){
            R.id.add_to_queue -> {
                val info = item.getMenuInfo() as AdapterContextMenuInfo
                val index = info.position
                queuedList.add(songsArray[index])

                val snackbar = Snackbar.make(findViewById(android.R.id.content), "Go to QueuedSongs Activity", Snackbar.LENGTH_LONG)
                snackbar.setAction("Go", View.OnClickListener{
                    val intent = Intent(this, QueuedSongsActivity::class.java)
                    intent.putStringArrayListExtra("queuedList", queuedList)
                    startActivity(intent)
                })
                snackbar.show()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }



}










