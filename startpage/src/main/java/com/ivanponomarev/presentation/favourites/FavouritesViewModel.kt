package com.ivanponomarev.presentation.favourites

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ivanponomarev.domain.Repo
import com.ivanponomarev.util.SharedPrefManager
import com.ivanponomarev.util.fromJson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
       private val sharedPrefManager: SharedPrefManager
) : ViewModel() {

    var favourites: MutableLiveData<List<Repo?>> = MutableLiveData()

    init {
        Log.i("favouritesViewModel", "Created")
    }

    fun fetchFavouritesData() {
        val sharedPreferencesContent = sharedPrefManager.getAll
        var favouriteItemsList = emptyList<Repo?>().toMutableList()

        sharedPreferencesContent.forEach { item ->

            var itemString = item.value as String
            favouriteItemsList.add(itemString.fromJson())

            Log.i("favouritesContent", item.value.toString())
        }

        favourites.postValue(favouriteItemsList)
        Log.i("favouritesRepo", favourites.toString())
    }
}