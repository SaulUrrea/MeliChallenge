package com.saulodev.melichallenge.ui.home.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.saulodev.melichallenge.domain.usecases.IGetPicturesByItemId
import com.saulodev.melichallenge.mocks.PicturesMock
import com.saulodev.melichallenge.ui.home.models.DetailItemViewState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


class DetailItemViewModelTest {

    @get:Rule
    val testInstanceTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DetailItemViewModel
    private val testScope = TestScope()
    private val getPicturesByItemIdMock: IGetPicturesByItemId = mockk()

    @Before
    fun setUp() {
        viewModel = DetailItemViewModel(
            getPicturesByItemId = getPicturesByItemIdMock
        )
    }


    @Test
    fun getDetailItemViewState() {
    }

    @Test
    fun `getPicturesForItem success`() = testScope.runTest {
        val id = "123"

        coEvery { getPicturesByItemIdMock.invoke(id) } returns
                Result.success(PicturesMock.getPicturesResponse())

        viewModel.getPicturesForItem(id)

        coVerify(exactly = 1) { getPicturesByItemIdMock.invoke(id) }
        assert(viewModel.detailItemViewState.value is DetailItemViewState.PicturesResultsFetched)
    }

    @Test
    fun `getPicturesForItem failure`() = testScope.runTest {
        val id = "123"

        coEvery { getPicturesByItemIdMock.invoke(id) } returns
                Result.failure(Exception("Network"))

        viewModel.getPicturesForItem(id)

        coVerify(exactly = 1) { getPicturesByItemIdMock.invoke(id) }
        assert(viewModel.detailItemViewState.value is DetailItemViewState.Error)
    }

    @Test
    fun `getPicturesForItem failure for empty pictures`() = testScope.runTest {
        val id = "123"

        coEvery { getPicturesByItemIdMock.invoke(id) } returns
                Result.success(PicturesMock.getPicturesResponse().copy(pictures = emptyList()))

        viewModel.getPicturesForItem(id)

        coVerify(exactly = 1) { getPicturesByItemIdMock.invoke(id) }
        assert(viewModel.detailItemViewState.value is DetailItemViewState.Error)
    }
}