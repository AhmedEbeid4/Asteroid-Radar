package com.udacity.asteroidradar.ui.main

import android.app.Application
import androidx.lifecycle.*
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.models.Asteroid
import com.udacity.asteroidradar.repository.AsteroidRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AsteroidDatabase.getInstance(application)
    private val repository = AsteroidRepository(database)

    private val weekAsteroidList = repository.asteroidList

    val pictureOfDay = repository.pictureOfDay

    private val todayAsteroidList = repository.todayAsteroidList

    val asteroidList: MediatorLiveData<List<Asteroid>> = MediatorLiveData()


    init {
        getAsteroidData()
    }


    private fun getAsteroidData() {
        viewModelScope.launch {
            repository.refreshAsteroidList()
            repository.getPictureOfTheDate()
            asteroidList.addSource(weekAsteroidList) {
                asteroidList.value = it
            }
        }
    }

    fun todayList() {
        removeSource()
        asteroidList.addSource(todayAsteroidList) {
            asteroidList.value = it
        }

    }

    fun weekList() {
        removeSource()
        asteroidList.addSource(weekAsteroidList) {
            asteroidList.value = it
        }

    }

    fun savedList() {
        removeSource()
        asteroidList.addSource(weekAsteroidList) {
            asteroidList.value = it
        }
    }

    private fun removeSource() {
        asteroidList.removeSource(todayAsteroidList)
        asteroidList.removeSource(weekAsteroidList)
    }

}
class Factory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(app) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}