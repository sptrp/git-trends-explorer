package com.ivanponomarev.util

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


inline fun <reified T> T.toJson(jsonInstance: Json? = null): String =
    (jsonInstance ?: Json).encodeToString(this)
