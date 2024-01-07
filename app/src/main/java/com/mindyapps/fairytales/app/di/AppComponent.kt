package com.mindyapps.fairytales.app.di

import android.content.ContentResolver
import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppComponent {

    @Provides
    @Singleton
    fun provideResource(context: Context): Resources = context.resources


    @Provides
    fun contentProvide(context: Context): ContentResolver = context.contentResolver

}