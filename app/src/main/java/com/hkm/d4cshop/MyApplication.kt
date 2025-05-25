package com.hkm.d4cshop

import android.app.Application
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}