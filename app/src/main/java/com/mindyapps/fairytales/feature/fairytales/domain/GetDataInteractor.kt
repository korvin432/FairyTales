package com.mindyapps.fairytales.feature.fairytales.domain

import javax.inject.Inject

class GetDataInteractor @Inject constructor(val repository: FairyTalesRepository) {
    operator fun invoke(): List<FairyTaleViewData> {
        return repository.getData()
    }
}