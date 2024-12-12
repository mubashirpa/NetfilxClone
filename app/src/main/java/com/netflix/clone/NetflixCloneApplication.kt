package com.netflix.clone

import android.app.Application
import com.netflix.clone.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class NetflixCloneApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@NetflixCloneApplication)
            modules(appModule)
        }
    }
}
