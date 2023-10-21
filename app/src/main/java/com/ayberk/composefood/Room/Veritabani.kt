package com.example.yemekleruygulamasi.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ayberk.composefood.Room.YemeklerDao
import com.ayberk.composefood.entity.Yemekler


@Database(entities = [Yemekler::class],version = 1)
abstract class Veritabani : RoomDatabase() {
    abstract fun yemeklerDao(): YemeklerDao

    companion object{
        var INSTANCE:Veritabani? = null

        fun veritabaniErisim(context: Context):Veritabani?{
            if (INSTANCE == null){

                synchronized(Veritabani::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        Veritabani::class.java,
                        "yemekler.sqlite").createFromAsset("yemekler.sqlite").build()
                }
            }
            return INSTANCE
        }
    }
}