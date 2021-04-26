package com.ivanponomarev.domain

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Repo(
    @SerialName("author") val author: String?,
    @SerialName("name") val name: String?,
    @SerialName("avatar") val avatar: String?,
    @SerialName("url") val url: String?,
    @SerialName("description") val description: String?,
    @SerialName("language") val language: String?,
    @SerialName("languageColor") val languageColor: String?,
    @SerialName("stars") val stars: Int?,
    @SerialName("forks") val forks: Int?,
    @SerialName("currentPeriodStars") val currentPeriodStars: Int?,
    @SerialName("builtBy") val builtBy: List<User?>,
)

@Serializable
data class User(
    @SerialName("username") val username: String?,
    @SerialName("href") val href: String?,
    @SerialName("avatar") val avatar: String?
)
