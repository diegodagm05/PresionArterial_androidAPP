package com.delnortedevs.pappcontrol

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Profile::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun DaoPrincipal() : ProfileDao
}