package com.ivanponomarev.util

import android.content.Context
import com.ivanponomarev.util.fromJson
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPrefManager @Inject constructor(@ApplicationContext context: Context) {
    private val prefs = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

    var item: String
        get() = prefs.getString("", null) ?: "my default value"
        set(value) = prefs.edit().putString(value.substring(value.indexOf("name") + 7, value.indexOf("avatar") - 3), value)
                .apply()


    var getAll: MutableMap<String, *> = prefs.all
}