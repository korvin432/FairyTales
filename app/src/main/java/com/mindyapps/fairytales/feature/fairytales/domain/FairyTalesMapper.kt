package com.mindyapps.fairytales.feature.fairytales.domain

import com.mindyapps.fairytales.feature.fairytales.data.SimpleResponseModel
import javax.inject.Inject

class FairyTalesMapper @Inject constructor() {
    fun mapToViewDataEntityList(simpleResponse: List<SimpleResponseModel>): List<FairyTalesViewData> {
        return simpleResponse.sortedBy { entity -> entity.id }.map { entity ->
            FairyTalesViewData(id = entity.id, entity.title, entity.description)
        }
    }
}