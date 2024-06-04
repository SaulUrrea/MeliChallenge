package com.saulodev.melichallenge.domain.usecases

import com.saulodev.melichallenge.domain.repositories.IItemsRepository
import com.saulodev.melichallenge.mocks.ItemMock
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetItemByNameUseCaseTest {

    private lateinit var getItemByNameUseCase: GetItemByNameUseCase
    private val repository: IItemsRepository = mockk()

    @Before
    fun setup() {
        getItemByNameUseCase = GetItemByNameUseCase(repository)
    }

    @Test
    fun `invoke should return Result failure when name is blank`() = runTest {
        val name = ""

        val result = getItemByNameUseCase.invoke(name)

        assertTrue(result.isFailure)
        assertEquals("Name cannot be empty", result.exceptionOrNull()?.message)
    }

    @Test
    fun `invoke should return Result success with ItemsResponseModel when name is valid`() =
        runTest {
            val name = "test"
            val expectedResponse = ItemMock.getItemResponseMock()
            coEvery { repository.searchItemByName(name) } returns (Result.success(expectedResponse))

            val result = getItemByNameUseCase.invoke(name)

            assertTrue(result.isSuccess)
            assertEquals(expectedResponse, result.getOrNull())
        }

    @Test
    fun `invoke should return Result failure when repository returns failure`() = runTest {
        val name = "test"
        val expectedException = Exception("Repository error")
        coEvery { repository.searchItemByName(name) } returns (Result.failure(expectedException))

        val result = getItemByNameUseCase.invoke(name)

        assertTrue(result.isFailure)
        assertEquals(expectedException, result.exceptionOrNull())
    }
}