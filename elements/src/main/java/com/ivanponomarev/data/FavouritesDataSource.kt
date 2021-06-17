package com.ivanponomarev.data

import android.content.Context

abstract class FavouritesDataSource {

    abstract suspend fun getFavourites()
}