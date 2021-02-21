package com.example.uip2

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog


class AlbumDetailsActivity : AppCompatActivity() {
    var albumSongsArray: ArrayList<String> = ArrayList()
    lateinit var adapter: ArrayAdapter<String>






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)

        var modalItems: Modal = intent.getSerializableExtra("data") as Modal;

        Log.e("name", modalItems.name.toString());
        val viewName = findViewById<TextView>(R.id.viewName)
        val viewImage = findViewById<ImageView>(R.id.viewImage)

        var holder: Array<String> = arrayOf()

        viewName.text = modalItems.name;
        var songsArray: Array<String> = arrayOf()
        if (modalItems.name.equals("image1")) {
            viewName.text = "This is Lany"
            holder  = arrayOf("Super Far", "ILYSB", "13",
                "Hericane", "Hurts"
            )
            for (string in holder){
                albumSongsArray.add(string)
            }
        } else if (modalItems.name.equals("image2")) {
            viewName.text ="Malibu Nights"
            holder = arrayOf("Malibu Nights",
                "Thick and Thin", "I Don't Wanna Love You Anymore", "Thru These Tears",
                "If You See Her"
            )
            for (string in holder){
                albumSongsArray.add(string)
            }
        } else if (modalItems.name.equals("image3")) {
            viewName.text = "Mama's Boy"
            holder = arrayOf("Heart Won't Let Me", "Good Guys",
                "Nobody Else", "Paper", "If This Is The Last Time"
            )
            for (string in holder){
                albumSongsArray.add(string)
            }
        }

        viewImage.setImageResource(modalItems.image!!);
        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, albumSongsArray)
        val songs_list: ListView = findViewById<ListView>(R.id.songs_list)

        songs_list.adapter = adapter

        registerForContextMenu(songs_list)


    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.album_item_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.remove -> {
                val dialogBuilder = AlertDialog.Builder(this)

                dialogBuilder.setMessage("Do you want to remove song?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", DialogInterface.OnClickListener{
                            dialog, which ->
                            val info = item.getMenuInfo() as AdapterView.AdapterContextMenuInfo
                            val index = info.position
                            albumSongsArray.removeAt(index);

                            val toast = Toast.makeText(applicationContext, "Song has been removed", Toast.LENGTH_SHORT)
                            adapter.notifyDataSetChanged();
                            toast.show()
                        })
                        .setNegativeButton("No",DialogInterface.OnClickListener{
                            dialog, which->
                            dialog.cancel()
                        })
                val alert: AlertDialog = dialogBuilder.create()
                alert.setTitle("Remove Song")
                alert.show()
                true
            }

            else -> super.onContextItemSelected(item)
        }
    }

}

