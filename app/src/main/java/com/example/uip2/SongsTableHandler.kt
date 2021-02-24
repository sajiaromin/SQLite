package com.example.uip2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.uip2.model.songs

class SongsTableHandler(var context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private val DATABASE_VERSION = 1
        private  val DATABASE_NAME = "songs_database"
        private val TABLE_NAME = "songs"
        private val COL_ID = "id"
        private val COL_TITLE ="title"
        private val COL_ALBUM = "album"
        private val COL_ARTIST = "artist"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
      val query = "CREATE TABLE "+ TABLE_NAME+"( "+ COL_ID+" INTEGER PRIMARY KEY," + COL_TITLE +"TEXT," + COL_ALBUM+ "TEXT," + COL_ARTIST+" TEXT)"
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP IF EXISTS " + TABLE_NAME)
        onCreate(p0)
    }
    fun create(songs: songs): Boolean {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_TITLE, songs.title)
        contentValues.put(COL_ALBUM,songs.album)
        contentValues.put(COL_ARTIST, songs.artist)

        val result = database.insert(TABLE_NAME, null, contentValues)
        if(result == (0).toLong()){
            return true
        }
        return false
    }

}