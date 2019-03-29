package com.hdhuu.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hdhuu.data.post.models.CandyDTO

@Database(entities = [CandyDTO::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postDAO(): CandyDAO

    private var INSTANCE: AppDatabase? = null

    private val sLock = Any()

    fun getInstance(context: Context): AppDatabase {
        if (INSTANCE == null) {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            AppDatabase::class.java, "appDB.db")
                            .build()
                }
                return INSTANCE!!
            }
        }
        return INSTANCE!!
    }

}