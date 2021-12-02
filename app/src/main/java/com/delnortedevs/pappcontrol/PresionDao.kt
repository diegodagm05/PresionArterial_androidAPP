package com.delnortedevs.pappcontrol

import androidx.room.*

@Dao
interface PresionDao {
    @Query("SELECT * from Presion")
    suspend fun getPresion(): List<Presion>
    @Query("DELETE from Presion")
    suspend fun deleteAllPresion()
    @Insert
    suspend fun addPresion(p: Presion)
    @Update
    suspend fun updatePresion(p: Presion)
    @Delete
    suspend fun deletePresion(p: Presion)
}