package com.ivanponomarev.di

import com.ivanponomarev.data.ReposDataSource
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit

@InstallIn(ActivityRetainedComponent::class)
@Module
object NetworkModule {

    private const val BASE_URL = "https://gtrend.yapie.me"

    @Provides
    fun provideConverterFactory(): Converter.Factory {
        val kotlinSerializationConverterFactory: Converter.Factory by lazy {
            Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
                .asConverterFactory("application/json".toMediaType())
        }
        return kotlinSerializationConverterFactory
    }

    @Provides
    fun provideRetrofit(converterFactory: Converter.Factory) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()).build())
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    fun provideReposDataSource(retrofit: Retrofit) : ReposDataSource {
        return retrofit.create(ReposDataSource::class.java)
    }

}