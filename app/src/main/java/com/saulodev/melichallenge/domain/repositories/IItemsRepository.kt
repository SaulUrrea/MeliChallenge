package com.saulodev.melichallenge.domain.repositories

import com.saulodev.melichallenge.data.dto.ItemsResponse
import com.saulodev.melichallenge.domain.models.ItemsResponseModel
import com.saulodev.melichallenge.domain.models.PicturesItemResponseModel


/**
 * Repository interface for accessing item data.
 */
interface IItemsRepository {

    /**
     * Searches for items by their name.
     *
     * @param itemName The name of the item to search for.
     * @return A [Result] object wrapping either a [Success] containing the [ItemsResponse]
     *         with the search results or an [Error] containing an [Exception] if the
     *         search failed.
     */
    suspend fun searchItemByName(itemName: String): Result<ItemsResponseModel>


    /**
     * Suspended function to fetch pictures by item ID.
     *
     * @param itemId The ID of the item for which pictures are requested.
     * @return A [Result] containing a [PicturesItemResponseModel] if the operation is successful,
     *         or an error if the operation fails.
     */
    suspend fun getPicturesByItemId(itemId: String): Result<PicturesItemResponseModel>

}