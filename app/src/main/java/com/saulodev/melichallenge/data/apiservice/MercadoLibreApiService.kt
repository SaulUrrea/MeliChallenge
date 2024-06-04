package com.saulodev.melichallenge.data.apiservice

import com.saulodev.melichallenge.data.dto.ItemsResponse
import com.saulodev.melichallenge.data.dto.PicturesItemResponse
import com.saulodev.melichallenge.utils.Constants.ITEM_PICTURES_ENDPOINT
import com.saulodev.melichallenge.utils.Constants.SEARCH_ITEMS_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Retrofit API Service definition for searching items by name.
 */
interface MercadoLibreApiService {

    /**
     * Searches for items by their name.
     *
     * @param site The site identifier (e.g., "MLA").
     * @param itemName The name of the item to search for.
     * @return A [Response] object containing a list of items that match the search query,
     *         wrapped in an [ItemsResponse] object.
     */
    @GET(SEARCH_ITEMS_ENDPOINT)
    suspend fun searchItemByName(
        @Path("site") site: String,
        @Query("q") itemName: String
    ): Response<ItemsResponse>

    /**
     * Retrieves pictures for a specific item by its ID.
     *
     * @param itemId The ID of the item.
     * @return A [Response] object containing the pictures of the item, wrapped in an [ItemsResponse] object.
     */
    @GET(ITEM_PICTURES_ENDPOINT)
    suspend fun getPicturesByItemId(
        @Path("itemId") itemId: String
    ): Response<PicturesItemResponse>

}