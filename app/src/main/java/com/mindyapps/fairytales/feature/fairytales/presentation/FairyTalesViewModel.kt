package com.mindyapps.fairytales.feature.fairytales.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mindyapps.fairytales.base.presentation.BaseViewModel
import com.mindyapps.fairytales.core.mapDistinct
import com.mindyapps.fairytales.feature.fairytales.domain.FairyTalesViewData
import com.mindyapps.fairytales.feature.fairytales.domain.GetDataInteractor
import com.mindyapps.fairytales.feature.fairytales.presentation.FairyTalesViewState
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

    fun onItemSelect(item: FairyTalesViewData) {
        // navigate to otherScreen
        // simple : navigateTo(SimpleFragmentDirections.toOtherScreen(item.id))
    }

}