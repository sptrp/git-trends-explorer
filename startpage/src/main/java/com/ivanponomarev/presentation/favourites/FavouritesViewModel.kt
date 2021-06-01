package com.ivanponomarev.presentation.favourites

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ivanponomarev.domain.Repo


class FavouritesViewModel : ViewModel() {

    var favourites: MutableLiveData<List<Repo?>> = MutableLiveData()

    init {
        Log.i("favouritesViewModel", "Created")
    }
}