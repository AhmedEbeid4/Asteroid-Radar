package com.udacity.asteroidradar.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.udacity.asteroidradar.models.Constants
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(Constants.BASE_URL)
    .build()


interface NasaApiClient{
    @GET("neo/rest/v1/feed")
    suspend fun getAsteroidsByDate(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String
    ): String

    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(
        @Query("api_key") apiKey: String
    ): String

    @GET("planetary/apod")
    suspend fun getPictureOfDay(@Query("api_key") apiKey: String): String
}


object NasaApi {
    val retrofitService: NasaApiClient by lazy { retrofit.create(NasaApiClient::class.java) }
}