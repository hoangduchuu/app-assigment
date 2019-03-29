package com.hdhuu.di

import androidx.room.Room
import com.hdhuu.data.room.AppDatabase
import com.hdhuu.domain.scheduler.PostExecutionThread
import com.hdhuu.domain.scheduler.ThreadExecutor
import com.hdhuu.scheduler.JobExecutor
import com.hdhuu.scheduler.UIThread
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Huu Hoang on 3/29/19.
 */
/**
 * any modules will be located over here
 */

val applicationModule = module(override = true) {
    single<ThreadExecutor> { JobExecutor() }
    single<PostExecutionThread> { UIThread() }

    single { Room.databaseBuilder(androidContext(),
        AppDatabase::class.java, "appDB.db")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build() }
    factory { get<AppDatabase>().candyDAO() }
}