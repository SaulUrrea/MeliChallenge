package com.saulodev.melichallenge.core.di

import com.saulodev.melichallenge.data.apiservice.MercadoLibreApiService
import com.saulodev.melichallenge.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides an implementation of MercadoLibreApiService.
     *
     * @param retrofit The Retrofit instance used to create the service.
     * @return An instance of MercadoLibreApiService.
     */
    @Provides
    fun provideItemsService(
        retrofit: Retrofit
    ): MercadoLibreApiService = retrofit.create(MercadoLibreApiService::class.java)

    /**
     * Provides a singleton Retrofit instance.
     *
     * @return The configured Retrofit instance.
     */
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()
    }

    /**
     * Configures and provides an OkHttpClient instance.
     *
     * @return The configured OkHttpClient instance.
     */
    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .build()
    }
}