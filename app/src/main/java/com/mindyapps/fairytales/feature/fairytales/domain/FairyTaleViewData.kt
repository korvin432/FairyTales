package com.mindyapps.fairytales.feature.fairytales.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FairyTaleViewData(val id: Int, val title: String, val description: String): Parcelable
