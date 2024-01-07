package com.mindyapps.fairytales.feature.fairytales.core

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import com.mindyapps.fairytales.feature.fairytales.data.SimpleApi
import com.mindyapps.fairytales.feature.fairytales.data.FairyTalesRepositoryImpl
import com.mindyapps.fairytales.feature.fairytales.domain.FairyTalesRepository
import com.mindyapps.fairytales.app.di.NetworkModule
import com.mindyapps.fairytales.app.di.ViewModelBuilder
import com.mindyapps.fairytales.app.di.ViewModelKey
import com.mindyapps.fairytales.feature.fairytales.presentation.FairyTalesFragment
import com.mindyapps.fairytales.feature.fairytales.presentation.FairyTalesViewModel
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
abstract class FairyTalesModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun fairyTalesFragment(): FairyTalesFragment

    @Binds
    @IntoMap
    @ViewModelKey(FairyTalesViewModel::class)
    abstract fun bindViewModel(viewModel: FairyTalesViewModel): ViewModel

    @Singleton
    @Binds
    abstract fun provideFairyTalesRepository(repository: FairyTalesRepositoryImpl): FairyTalesRepository


    companion object {
        @Provides
        fun provideApi(retrofit: Retrofit): SimpleApi = retrofit.create()
    }

}