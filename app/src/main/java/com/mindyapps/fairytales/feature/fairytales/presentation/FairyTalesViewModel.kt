package com.mindyapps.fairytales.feature.fairytales.presentation

import androidx.navigation.fragment.FragmentNavigator
import com.mindyapps.fairytales.base.presentation.BaseViewModel
import com.mindyapps.fairytales.core.mapDistinct
import com.mindyapps.fairytales.feature.fairytales.domain.FairyTaleViewData
import com.mindyapps.fairytales.feature.fairytales.domain.GetDataInteractor
import javax.inject.Inject

class FairyTalesViewModel @Inject constructor(private val getDataInteractor: GetDataInteractor) :
    BaseViewModel<FairyTalesViewState>() {

    val data = liveState.mapDistinct { it.data }

    init {
        state = FairyTalesViewState(
            data = mutableListOf()
        )
    }

    fun onViewCreated() {
        state = state.copy(data = getDataInteractor.invoke())
    }

    fun onItemSelect(item: FairyTaleViewData, extras: FragmentNavigator.Extras) {
        // navigate to otherScreen
        // simple : navigateTo(SimpleFragmentDirections.toOtherScreen(item.id))
        navigateTo(
            FairyTalesFragmentDirections.actionNavigationFairyTalesToNavigationFairyTale(item),
            extras
        )
    }

}