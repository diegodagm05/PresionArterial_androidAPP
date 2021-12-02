package com.delnortedevs.pappcontrol

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profile (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @NonNull @ColumnInfo(name="nombre") var nombre: String,
    @NonNull @ColumnInfo(name = "telefono") var telefono: String,
    @NonNull @ColumnInfo(name = "edad") var edad: Int,
    @NonNull @ColumnInfo(name = "circunferencia") var circunferencia: Double,
    @NonNull @ColumnInfo(name = "peso") var peso: Double,
    @NonNull @ColumnInfo(name = "sexo") var sexo: String,
        ){
    constructor(nombre: String, telefono: String, edad: Int, circunferencia: Double,
                peso: Double, sexo: String): this(0, nombre, telefono, edad, circunferencia, peso, sexo)

}