package com.mindyapps.fairytales.app

import com.mindyapps.fairytales.app.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

open class AppApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        // init other component, service
    }


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.factory().create(applicationContext)
    }
}