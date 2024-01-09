package com.mindyapps.fairytales.feature.fairytales.domain

interface FairyTalesRepository {
    fun getData(): List<FairyTaleViewData>
}