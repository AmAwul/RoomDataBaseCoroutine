package com.awul.roomdatabasecoroutine.room

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [PersonModel::class], version = 1, exportSchema = true)
abstract class PersonDB: RoomDatabase() {
    abstract fun personDao(): PersonDao

    companion object {

      private lateinit var roomDB: PersonDB

        fun getDb(context: Context): PersonDB {
            roomDB = Room.databaseBuilder(
                context, PersonDB::class.java,
                "person.db"
            ).addCallback(rommCallback)
                   .allowMainThreadQueries()
//                .addMigrations(MIGRATION_1_2)
                .build()
                .also {
                    Log.d("<ROOM_TAG>", it?.openHelper?.writableDatabase?.path ?: "")
                }
            return roomDB
        }

        val rommCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)



            }
        }



    }

}