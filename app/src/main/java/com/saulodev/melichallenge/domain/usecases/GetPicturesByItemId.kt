package com.saulodev.melichallenge.domain.usecases

import com.saulodev.melichallenge.domain.models.PicturesItemResponseModel
import com.saulodev.melichallenge.domain.repositories.IItemsRepository
import javax.inject.Inject


/**
 * Interface for retrieving pictures by item ID.
 */
interface IGetPicturesByItemId {
    /**
     * Retrieves pictures for a specific item by its ID using the provided repository.
     *
     * @param itemId The ID of the item.
     * @return A [Result] containing a [PicturesItemResponseModel] if the operation is successful,
     *         or an error if the operation fails.
     */
    suspend fun invoke(itemId: String): Result<PicturesItemResponseModel>
}

/**
 * Implementation of [IGetPicturesByItemId] that delegates the call to the repository.
 *
 * @property repository The repository to retrieve pictures from.
 */
class GetPicturesByItemId @Inject constructor(
    private val repository: IItemsRepository
) : IGetPicturesByItemId {

    /**
     * Retrieves pictures for a specific item by its ID using the provided repository.
     *
     * @param itemId The ID of the item.
     * @return A [Result] containing a [PicturesItemResponseModel] if the operation is successful,
     *         or an error if the operation fails.
     */
    override suspend fun invoke(itemId: String): Result<PicturesItemResponseModel> {
        if (itemId.isBlank()) {
            return Result.failure(Exception("Item ID cannot be empty"))
        }
        return repository.getPicturesByItemId(itemId)
    }
}

