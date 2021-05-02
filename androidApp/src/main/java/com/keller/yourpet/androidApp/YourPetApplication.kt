package com.keller.yourpet.androidApp

import android.app.Application
import com.keller.yourpet.shared.di.initKoin
import dagger.hilt.android.HiltAndroidApp
import org.koin.core.component.KoinComponent

@HiltAndroidApp
class YourPetApplication : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()

        initKoin()
    }
}
