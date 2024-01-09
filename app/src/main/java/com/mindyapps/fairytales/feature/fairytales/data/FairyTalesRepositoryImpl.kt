package com.mindyapps.fairytales.feature.fairytales.data

import com.mindyapps.fairytales.feature.fairytales.domain.FairyTalesMapper
import com.mindyapps.fairytales.feature.fairytales.domain.FairyTaleViewData
import com.mindyapps.fairytales.feature.fairytales.domain.FairyTalesRepository
import javax.inject.Inject

class FairyTalesRepositoryImpl @Inject constructor(
    private val mapper: FairyTalesMapper,
    private val api: SimpleApi
) : FairyTalesRepository {

    override fun getData(): List<FairyTaleViewData> {
        //  val response = getMyApi().getData()

        val response = mutableListOf<SimpleResponseModel>()
        response.addAll(listOf(
            SimpleResponseModel(
                1,
                "Название первой сказки",
                "Краткое какое нибудь описание первой сказки"
            ),
            SimpleResponseModel(
                2,
                "Название второй сказки",
                "Краткое какое нибудь описание второй сказки"
            ))
        )

        return mapper.mapToViewDataEntityList(response)
    }

}