package com.mindyapps.fairytales.feature.profile.core

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import com.mindyapps.fairytales.app.di.ViewModelBuilder
import com.mindyapps.fairytales.app.di.ViewModelKey
import com.mindyapps.fairytales.feature.profile.presentation.ProfileFragment
import com.mindyapps.fairytales.feature.profile.presentation.ProfileViewModel

@Module
abstract class ProfileModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun profileFragment(): ProfileFragment

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindViewModel(viewModel: ProfileViewModel): ViewModel
//
//    @Singleton
//    @Binds
//    abstract fun provideFairyTalesRepository(repository: FairyTalesRepositoryImpl): FairyTalesRepository
//
//
//    companion object {
//        @Provides
//        fun provideApi(retrofit: Retrofit): SimpleApi = retrofit.create()
//    }

}