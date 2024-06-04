package com.saulodev.melichallenge.data.repositories

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.saulodev.melichallenge.data.apiservice.MercadoLibreApiService
import com.saulodev.melichallenge.domain.mapper.toItemsResponseModel
import com.saulodev.melichallenge.domain.mapper.toPicturesItemResponseModel
import com.saulodev.melichallenge.domain.models.ItemsResponseModel
import com.saulodev.melichallenge.domain.models.PicturesItemResponseModel
import com.saulodev.melichallenge.domain.repositories.IItemsRepository
import com.saulodev.melichallenge.utils.Constants.SITE_ARG_ID
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemsRepository @Inject constructor(
    private val mercadoLibreApiService: MercadoLibreApiService,
) : IItemsRepository {

    /**
     * Searches for items by name and logs the result.
     *
     * @param itemName The name of the item to search for.
     * @return A `Result` object wrapping either the successful `ItemsResponseModel` or an `Exception`
     *         in case of failure.
     */
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun searchItemByName(itemName: String): Result<ItemsResponseModel> {
        return try {
            Log.d("ItemsRepository", "Searching for items by name: $itemName")
            val response = mercadoLibreApiService.searchItemByName(
                site = SITE_ARG_ID,
                itemName = itemName
            )

            if (response.isSuccessful) {
                Result.success(response.body()?.toItemsResponseModel()!!)
            } else {
                Result.failure(Exception("Network call failed with error code ${response.code()}"))
            }
        } catch (ioException: IOException) {
            Result.failure(ioException)
        } catch (httpException: HttpException) {
            Result.failure(httpException)
        }
    }

    /**
     * Retrieves pictures for a specific item by its ID.
     *
     * @param itemId The ID of the item.
     * @return A [Result] containing a [PicturesItemResponseModel] if the operation is successful,
     *         or an error if the operation fails.
     */
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getPicturesByItemId(itemId: String): Result<PicturesItemResponseModel> {
        return try {
            Log.d("getPicturesByItemId", "Searching pictures for item : $itemId")
            val response = mercadoLibreApiService.getPicturesByItemId(
                itemId = itemId
            )

            if (response.isSuccessful) {
                Result.success(response.body()?.toPicturesItemResponseModel()!!)
            } else {
                Result.failure(Exception("Network call failed with error code ${response.code()}"))
            }
        } catch (ioException: IOException) {
            Result.failure(ioException)
        } catch (httpException: HttpException) {
            Result.failure(httpException)
        }
    }
}
