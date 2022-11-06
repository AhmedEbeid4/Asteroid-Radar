package com.udacity.asteroidradar.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.squareup.moshi.Moshi
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.api.getImage
import com.udacity.asteroidradar.api.parseAsteroidsJson
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.models.Asteroid
import com.udacity.asteroidradar.models.Constants
import com.udacity.asteroidradar.models.PictureOfDay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
class AsteroidRepository (private val database:AsteroidDatabase){

    val asteroidList: LiveData<List<Asteroid>>
        get() = database.asteroidDao().getAll()

    val todayAsteroidList: LiveData<List<Asteroid>>
        get() = database.asteroidDao().getTodayAsteroid(Constants.getCurrentDate())

    val pictureOfDay: LiveData<PictureOfDay>
        get() = database.pictureDao().get()


    suspend fun refreshAsteroidList() {
        withContext(Dispatchers.IO) {
            try {
                val asteroid = NasaApi.retrofitService.getAsteroids(Constants.API_KEY)
                val json = JSONObject(asteroid)
                val data = parseAsteroidsJson(json)
                println(data.size)
                database.asteroidDao().deleteAll()
                for(i in data.indices){
                    database.asteroidDao().addAsteroid(data[i])
                    println(data[i])
                }

            } catch (e: Exception) {
            }
        }
    }


    suspend fun getPictureOfTheDate() {
        withContext(Dispatchers.IO) {
            try {
                println("before pic")
                val response = NasaApi.retrofitService.getPictureOfDay(
                    Constants.API_KEY
                )
                val pic = getImage(JSONObject(response))
                database.pictureDao().updateData(pic!!)
            } catch (e: Exception) {
            }
        }
    }
}
