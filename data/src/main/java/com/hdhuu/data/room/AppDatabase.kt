package com.hdhuu.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hdhuu.data.post.models.CandyDTO

@Database(entities = [CandyDTO::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun candyDAO(): CandyDAO

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "acandy.dbm"
            )
                .build()
    }

}