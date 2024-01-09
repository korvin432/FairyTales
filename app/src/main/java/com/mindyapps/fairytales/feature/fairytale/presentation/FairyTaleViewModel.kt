package com.mindyapps.fairytales.feature.fairytale.presentation

import com.mindyapps.fairytales.base.presentation.BaseViewModel
import com.mindyapps.fairytales.core.mapDistinct
import com.mindyapps.fairytales.feature.fairytales.domain.FairyTaleViewData
import javax.inject.Inject

class FairyTaleViewModel @Inject constructor() :
    BaseViewModel<FairyTaleViewState>() {

    val data = liveState.mapDistinct { it.data }

    init {
        state = FairyTaleViewState(
            data = null
        )
    }

    fun onViewCreated() {
        //state = state.copy(data = getDataInteractor.invoke())
    }

    fun addToFavorites(item: FairyTaleViewData) {

    }

}