package com.ivanponomarev.domain

import android.content.Context
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PrefManager @Inject constructor(@ApplicationContext context: Context) {
    private val prefs = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    var item: String
        get() = prefs.getString("favourites", null) ?: "my default value"
        set(value) = prefs.edit().putString("favourites", value).apply()
}