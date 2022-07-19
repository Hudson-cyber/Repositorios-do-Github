package com.hudson.repositoriosgithub.core

import android.app.Application
import com.hudson.repositoriosgithub.di.locationModule
import org.koin.core.context.startKoin

import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(locationModule))
        }
    }

}