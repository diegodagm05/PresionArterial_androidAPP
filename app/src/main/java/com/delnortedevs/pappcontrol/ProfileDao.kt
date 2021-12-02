package com.delnortedevs.pappcontrol

import androidx.room.*

@Dao
interface ProfileDao {
    @Query("Select * from Profile")
    suspend fun getProfile(): List<Profile>

    @Query("DELETE from Profile")
    suspend fun deleteAll()

    @Insert
    suspend fun addProfile(profile: Profile)

    @Update
    suspend fun updateProfile(profile: Profile)

    @Delete
    suspend fun deleteProfile(profile: Profile)

}