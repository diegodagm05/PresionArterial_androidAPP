package com.delnortedevs.pappcontrol

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Presion (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @NonNull @ColumnInfo(name="sistolic")var sistolic: Int,
    @NonNull @ColumnInfo(name="diastolic")var diastolic: Int,
    @NonNull @ColumnInfo(name="pulse")var pulse: Int
)
{
    constructor(sistolic: Int, diastolic: Int, pulse: Int) : this(0, sistolic, diastolic, pulse)
}