//package com.hdhuu.data.room
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.hdhuu.data.post.models.CandyDTO
//
//@Database(entities = arrayOf(CandyDTO::class), version = 2)
//abstract class ARAPPDatabase : RoomDatabase() {
//
//    abstract fun candyDAO(): CandyDAO
//
//    private var INSTANCE: ARAPPDatabase? = null
//
//    private val sLock = Any()
//
//    //    fun getInstance(context: Context): ARAPPDatabase {
////        if (INSTANCE == null) {
////            synchronized(sLock) {
////                if (INSTANCE == null) {
////                    INSTANCE = Room.databaseBuilder(context.applicationContext,
////                            ARAPPDatabase::class.java, "appDB.db")
////                            .build()
////                }
////                return INSTANCE!!
////            }
////        }
////        return INSTANCE!!
////    }
//
//
//}