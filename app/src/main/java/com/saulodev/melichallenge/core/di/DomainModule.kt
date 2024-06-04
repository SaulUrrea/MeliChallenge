package com.saulodev.melichallenge.core.di

import com.saulodev.melichallenge.data.repositories.ItemsRepository
import com.saulodev.melichallenge.domain.usecases.GetItemByNameUseCase
import com.saulodev.melichallenge.domain.usecases.GetPicturesByItemId
import com.saulodev.melichallenge.domain.usecases.IGetItemByNameUseCase
import com.saulodev.melichallenge.domain.usecases.IGetPicturesByItemId
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    /**
     * Provides an implementation of IGetItemByNameUseCase.
     *
     * @param itemsRepository The repository to be used by the use case.
     * @return An instance of GetItemByNameUseCase.
     */
    @Provides
    fun provideGetItemByNameUseCase(
        itemsRepository: ItemsRepository
    ): IGetItemByNameUseCase = GetItemByNameUseCase(
        repository = itemsRepository
    )

    /**
     * Provides an instance of [IGetPicturesByItemId] using the provided [ItemsRepository].
     *
     * @param itemsRepository The repository to retrieve pictures from.
     * @return An instance of [IGetPicturesByItemId].
     */
    @Provides
    fun provideGetPicturesByItemId(
        itemsRepository: ItemsRepository
    ): IGetPicturesByItemId = GetPicturesByItemId(
        repository = itemsRepository
    )

}