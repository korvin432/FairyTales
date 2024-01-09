package com.mindyapps.fairytales.feature.fairytale.core

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import com.mindyapps.fairytales.feature.fairytale.data.FairyTaleRepositoryImpl
import com.mindyapps.fairytales.app.di.NetworkModule
import com.mindyapps.fairytales.app.di.ViewModelBuilder
import com.mindyapps.fairytales.app.di.ViewModelKey
import com.mindyapps.fairytales.feature.fairytale.domain.FairyTaleRepository
import com.mindyapps.fairytales.feature.fairytale.presentation.FairyTaleFragment
import com.mindyapps.fairytales.feature.fairytale.presentation.FairyTaleViewModel
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
abstract class FairyTaleModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun fairyTaleFragment(): FairyTaleFragment

    @Binds
    @IntoMap
    @ViewModelKey(FairyTaleViewModel::class)
    abstract fun bindViewModel(viewModel: FairyTaleViewModel): ViewModel

    @Singleton
    @Binds
    abstract fun provideFairyTaleRepository(repository: FairyTaleRepositoryImpl): FairyTaleRepository

}