package com.netflix.clone.di

import androidx.room.Room
import com.netflix.clone.data.local.database.NetflixDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule =
    module {
        single<NetflixDatabase> {
            Room
                .databaseBuilder(
                    context = androidContext(),
                    klass = NetflixDatabase::class.java,
                    name = "netflix-database",
                ).build()
        }
    }
