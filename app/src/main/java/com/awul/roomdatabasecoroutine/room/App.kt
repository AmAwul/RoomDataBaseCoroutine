package com.awul.roomdatabasecoroutine.room

import android.app.Application

class App: Application() {
    companion object{
        lateinit var db: PersonDB
    }

    override fun onCreate() {
        super.onCreate()
        db = PersonDB.getDb(applicationContext)
    }
}