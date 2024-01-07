package com.mindyapps.fairytales.feature.fairytales.data

import retrofit2.http.GET

interface SimpleApi {

    @GET("/main")
    fun getData(): List<SimpleResponseModel>
}