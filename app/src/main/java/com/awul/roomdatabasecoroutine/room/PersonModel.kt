package com.awul.roomdatabasecoroutine.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "person")
data class PersonModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "pName")
    val name: String = "",

    @ColumnInfo(name = "pNumber")
    val phone: String = ""
)
