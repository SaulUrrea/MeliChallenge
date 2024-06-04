package com.saulodev.melichallenge.domain.usecases

import com.saulodev.melichallenge.domain.repositories.IItemsRepository
import com.saulodev.melichallenge.mocks.PicturesMock
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetPicturesByItemIdTest {

    private lateinit var getPicturesByItemId: GetPicturesByItemId
    private val repository: IItemsRepository = mockk()

    @Before
    fun setup() {
        getPicturesByItemId = GetPicturesByItemId(repository)
    }

    @Test
    fun `invoke should return Result failure when itemId is blank`() = runTest {
        val itemId = ""

        val result = getPicturesByItemId.invoke(itemId)

        assertTrue(result.isFailure)
        assertEquals("Item ID cannot be empty", result.exceptionOrNull()?.message)
    }

    @Test
    fun `invoke should return Result success with PicturesItemResponseModel when itemId is valid`() =
        runTest {
            val itemId = "MLA123"
            val expectedResponse = PicturesMock.getPicturesResponse()
            coEvery { repository.getPicturesByItemId(itemId) } returns Result.success(
                expectedResponse
            )

            val result = getPicturesByItemId.invoke(itemId)

            assertTrue(result.isSuccess)
            assertEquals(expectedResponse, result.getOrNull())
        }

    @Test
    fun `invoke should return Result failure when repository returns failure`() = runTest {
        val itemId = "MLA123"
        val expectedException = Exception("Repository error")
        coEvery { repository.getPicturesByItemId(itemId) } returns Result.failure(expectedException)

        val result = getPicturesByItemId.invoke(itemId)

        assertTrue(result.isFailure)
        assertEquals(expectedException, result.exceptionOrNull())
    }
}