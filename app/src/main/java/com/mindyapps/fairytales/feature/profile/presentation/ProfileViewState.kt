package com.mindyapps.fairytales.feature.profile.presentation


import com.mindyapps.fairytales.base.presentation.ViewState
import com.mindyapps.fairytales.feature.fairytales.domain.FairyTaleViewData

data class ProfileViewState(val data: List<FairyTaleViewData>) : ViewState