package com.bhdev1215.movieinfo3

import android.app.Application
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
        Timber.plant(Timber.DebugTree())
    }
}