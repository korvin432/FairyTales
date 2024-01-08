package com.mindyapps.fairytales.feature.profile.presentation


import com.mindyapps.fairytales.base.presentation.ViewState
import com.mindyapps.fairytales.feature.fairytales.domain.FairyTalesViewData

data class ProfileViewState(val data: List<FairyTalesViewData>) : ViewState