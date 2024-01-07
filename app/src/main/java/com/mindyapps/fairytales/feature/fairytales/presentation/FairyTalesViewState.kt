package com.mindyapps.fairytales.feature.fairytales.presentation


import com.mindyapps.fairytales.base.presentation.ViewState
import com.mindyapps.fairytales.feature.fairytales.domain.FairyTalesViewData

data class FairyTalesViewState(val data: List<FairyTalesViewData>) : ViewState