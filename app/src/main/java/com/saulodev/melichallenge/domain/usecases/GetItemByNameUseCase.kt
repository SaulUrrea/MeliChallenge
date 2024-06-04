package com.saulodev.melichallenge.domain.usecases

import com.saulodev.melichallenge.domain.models.ItemsResponseModel
import com.saulodev.melichallenge.domain.repositories.IItemsRepository
import javax.inject.Inject


/**
 * Defines a use case for retrieving an item by its name.
 */
interface IGetItemByNameUseCase {
    /**
     * Executes the use case to fetch an item based on the provided name.
     *
     * @param name The name of the item to search for.
     * @return A `Result` object wrapping either the successful `ItemsResponseModel` or an `Exception`
     *         in case of failure.
     */
    suspend fun invoke(name: String): Result<ItemsResponseModel>
}


/**
 * Implementation of the `IGetItemByNameUseCase` that delegates the item retrieval logic to an
 * `IItemsRepository`.
 *
 * @property repository The repository responsible for fetching item data.
 */
class GetItemByNameUseCase @Inject constructor(
    private val repository: IItemsRepository
) : IGetItemByNameUseCase {

    override suspend fun invoke(name: String): Result<ItemsResponseModel> {
        if (name.isBlank()) {
            return Result.failure(Exception("Name cannot be empty"))
        }
        return repository.searchItemByName(name)
    }

}
