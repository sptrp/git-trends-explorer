package com.ivanponomarev.util

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


inline fun <reified T> T.toJson(jsonInstance: Json? = null): String =
    (jsonInstance ?: Json).encodeToString(this)

inline fun <reified T> String.fromJson(jsonInstance: Json? = null): T =
        (jsonInstance ?: Json).decodeFromString(this)