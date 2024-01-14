package com.mindyapps.fairytales.app.di


import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import com.mindyapps.fairytales.app.AppApplication
import com.mindyapps.fairytales.feature.fairytale.core.FairyTaleModule
import com.mindyapps.fairytales.feature.fairytales.core.FairyTalesModule
import com.mindyapps.fairytales.feature.profile.core.ProfileModule
import com.mindyapps.fairytales.feature.sounds.core.SoundsModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        FairyTalesModule::class,
        ProfileModule::class,
        FairyTaleModule::class,
        SoundsModule::class,
    ]
)
interface ApplicationComponent : AndroidInjector<AppApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}