package com.ivanponomarev.presentation.favourites

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ivanponomarev.domain.PrefManager
import com.ivanponomarev.domain.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
       private val sharedPrefManager: PrefManager
) : ViewModel() {

    var favourites: MutableLiveData<List<Repo?>> = MutableLiveData()

    init {
        Log.i("favouritesViewModel", "Created")
    }

    fun fetchFavouritesData() {
        val sharedPreferencesContents = sharedPrefManager.item
        Log.i("favouritesViewModel", sharedPreferencesContents)
    }

}