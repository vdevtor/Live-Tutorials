package com.vitorthemyth.tiktok2023tutorials

import android.app.Application
import com.orhanobut.hawk.Hawk
import com.vitorthemyth.tiktok2023tutorials.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp  : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger(Level.ERROR)
            androidContext(this@MyApp)
            modules(
             mainModule
            )
        }

        Hawk.init(this).build()
    }
}