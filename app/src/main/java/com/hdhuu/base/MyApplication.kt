package com.hdhuu.base

import android.app.Application
import com.hdhuu.di.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Huu Hoang on 3/29/19.
 */
class MyApplication : Application() {
    override fun onCreate(){
        super.onCreate()
        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(this@MyApplication)
            // declare modules
            modules(applicationModule)
        }
    }
}