package com.ivanponomarev.data

import android.content.Context
import dagger.Reusable
import javax.inject.Inject

@Reusable
class Favourites @Inject constructor (private val favouritesDataSource: FavouritesDataSource) {

    suspend fun getAllFavourites () = favouritesDataSource.getFavourites()
}