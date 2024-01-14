package com.mindyapps.fairytales.feature.sounds.core

import androidx.lifecycle.ViewModel
import com.mindyapps.fairytales.app.di.ViewModelBuilder
import com.mindyapps.fairytales.app.di.ViewModelKey
import com.mindyapps.fairytales.feature.sounds.SoundsFragment
import com.mindyapps.fairytales.feature.sounds.SoundsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class SoundsModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun soundsFragment(): SoundsFragment

    @Binds
    @IntoMap
    @ViewModelKey(SoundsViewModel::class)
    abstract fun bindViewModel(viewModel: SoundsViewModel): ViewModel

}