package com.awul.roomdatabasecoroutine.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface PersonDao {

    @Query("SELECT * FROM person")
    fun getAllPerson(): List<PersonModel>

    @Insert
    fun addPerson(personModel: PersonModel)

    @Update
    fun updatePerson(personModel: PersonModel)

    @Delete
    fun deletePerson(personModel: PersonModel)

}