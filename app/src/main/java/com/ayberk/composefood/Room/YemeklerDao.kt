package com.ayberk.composefood.Room

import androidx.room.Dao
import androidx.room.Query
import com.ayberk.composefood.entity.Yemekler

@Dao
interface YemeklerDao {
 @Query("SELECT * From yemekler")
 suspend fun tumYemekler():List<Yemekler>


}