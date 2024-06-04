package com.saulodev.melichallenge.core.di

import com.saulodev.melichallenge.data.apiservice.MercadoLibreApiService
import com.saulodev.melichallenge.data.repositories.ItemsRepository
import com.saulodev.melichallenge.domain.repositories.IItemsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideItemRepository(
        mercadoLibreApiService: MercadoLibreApiService
    ): IItemsRepository = ItemsRepository(
        mercadoLibreApiService = mercadoLibreApiService
    )

}