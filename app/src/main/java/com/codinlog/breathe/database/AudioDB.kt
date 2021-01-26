package com.codinlog.breathe.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codinlog.breathe.application.Application
import com.codinlog.breathe.database.dao.AudioDao
import com.codinlog.breathe.database.entity.AudioEntity

@Database(entities = [AudioEntity::class], version = 1, exportSchema = false)
abstract class AudioDB : RoomDatabase() {

    abstract fun getAudioDao(): AudioDao

    companion object {
        val Instance by lazy {
            Room.databaseBuilder(Application.mContext, AudioDB::class.java, "AudioDB").build()
        }
    }
}