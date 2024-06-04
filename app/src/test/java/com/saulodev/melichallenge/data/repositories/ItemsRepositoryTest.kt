package com.saulodev.melichallenge.data.repositories

import com.saulodev.melichallenge.data.apiservice.MercadoLibreApiService
import com.saulodev.melichallenge.data.dto.ItemsResponse
import com.saulodev.melichallenge.data.dto.PicturesItemResponse
import com.saulodev.melichallenge.domain.mapper.toItemsResponseModel
import com.saulodev.melichallenge.domain.mapper.toPicturesItemResponseModel
import com.saulodev.melichallenge.mocks.ItemMock.Companion.getItemResponseDtoMock
import com.saulodev.melichallenge.mocks.PicturesMock.Companion.getPicturesResponseDTO
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ItemsRepositoryTest {

    private lateinit var repository: ItemsRepository
    private val mercadoLibreApiService: MercadoLibreApiService = mockk()

    @Before
    fun setup() {
        repository = ItemsRepository(mercadoLibreApiService)
    }

    @Test
    fun `searchItemByName should return Result success with ItemsResponseModel when network call is successful`() =
        runTest {
            val itemName = "test"
            val successfulResponse = Response.success(getItemResponseDtoMock())
            coEvery {
                mercadoLibreApiService.searchItemByName(
                    any(),
                    any()
                )
            } returns successfulResponse

            val result = repository.searchItemByName(itemName)

            assertTrue(result.isSuccess)
            assertEquals(getItemResponseDtoMock().toItemsResponseModel(), result.getOrNull())
        }

    @Test
    fun `searchItemByName should return Result failure when network call fails`() = runTest {
        val itemName = "test"
        val errorResponseBody = "".toResponseBody("application/json".toMediaTypeOrNull())
        val errorResponse = Response.error<ItemsResponse>(400, errorResponseBody)
        coEvery { mercadoLibreApiService.searchItemByName(any(), any()) } returns errorResponse

        val result = repository.searchItemByName(itemName)

        assertTrue(result.isFailure)
        assertEquals("Network call failed with error code 400", result.exceptionOrNull()?.message)
    }

    @Test
    fun `getPicturesByItemId should return Result success with PicturesItemResponseModel when network call is successful`() =
        runTest {
            val itemId = "MLA123"
            val picturesItemResponse = getPicturesResponseDTO()
            val successfulResponse = Response.success(picturesItemResponse)
            coEvery { mercadoLibreApiService.getPicturesByItemId(itemId) } returns successfulResponse

            val result = repository.getPicturesByItemId(itemId)

            assertTrue(result.isSuccess)
            assertEquals(picturesItemResponse.toPicturesItemResponseModel(), result.getOrNull())
        }

    @Test
    fun `getPicturesByItemId should return Result failure when network call fails`() = runTest {
        val itemId = "MLA123"
        val errorResponseBody = "".toResponseBody("application/json".toMediaTypeOrNull())
        val errorResponse = Response.error<PicturesItemResponse>(400, errorResponseBody)
        coEvery { mercadoLibreApiService.getPicturesByItemId(itemId) } returns errorResponse

        val result = repository.getPicturesByItemId(itemId)

        assertTrue(result.isFailure)
        assertEquals("Network call failed with error code 400", result.exceptionOrNull()?.message)
    }
}