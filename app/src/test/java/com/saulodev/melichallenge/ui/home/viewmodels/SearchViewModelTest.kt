package com.saulodev.melichallenge.ui.home.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.saulodev.melichallenge.domain.models.ItemsResponseModel
import com.saulodev.melichallenge.domain.usecases.IGetItemByNameUseCase
import com.saulodev.melichallenge.mocks.ItemMock
import com.saulodev.melichallenge.ui.home.models.SearchFieldState
import com.saulodev.melichallenge.ui.home.models.SearchViewState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@FlowPreview
@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {

    @get:Rule
    val testInstanceTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SearchViewModel
    private val getItemByNameUseCaseMock: IGetItemByNameUseCase = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = SearchViewModel(
            getItemByNameUseCase = getItemByNameUseCaseMock
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initialize should only execute once`() = runTest(testDispatcher) {
        viewModel.initialize()
        viewModel.initialize()

        coVerify(exactly = 0) { getItemByNameUseCaseMock.invoke(any()) }
    }

    @Test
    fun `initialize with empty inputText should update ViewState to IdleScreen`() = runTest(testDispatcher) {
            val id = "123"

            coEvery { getItemByNameUseCaseMock.invoke(id) } returns
                    Result.success(ItemMock.getItemResponseMock())

            viewModel.updateInput("")

            viewModel.initialize()

            coVerify(exactly = 0) { getItemByNameUseCaseMock.invoke(id) }
            assertEquals(SearchViewState.IdleScreen, viewModel.searchViewState.first())
        }

    @Test
    fun `initialize with valid input sets SearchViewState NoResults state for empty response`() = runTest(testDispatcher) {
        val query = "zapatito"
        val itemResponseMock = ItemMock.getItemResponseWithEmptyLisyMock()

        coEvery { getItemByNameUseCaseMock.invoke(query) } returns Result.success(itemResponseMock)

        viewModel.initialize()

        viewModel.searchViewState.test {
            assertEquals(SearchViewState.IdleScreen, awaitItem())
            viewModel.updateInput(query)
            assertEquals(SearchViewState.Loading, awaitItem())
            assertEquals(SearchViewState.NoResults, awaitItem())
        }

        coVerify(exactly = 1) { getItemByNameUseCaseMock.invoke(query) }
    }
    @Test
    fun `initialize with valid input sets SearchViewState SearchResultsFetched state`() = runTest(testDispatcher) {
        val query = "zapatito"
        val itemResponseMock = ItemMock.getItemResponseMock()

        coEvery { getItemByNameUseCaseMock.invoke(query) } returns Result.success(itemResponseMock)

        viewModel.initialize()

        viewModel.searchViewState.test {
            assertEquals(SearchViewState.IdleScreen, awaitItem())
            viewModel.updateInput(query)
            assertEquals(SearchViewState.Loading, awaitItem())
            assertEquals(
                SearchViewState.SearchResultsFetched(itemResponseMock.items),
                awaitItem()
            )
        }

        coVerify(exactly = 1) { getItemByNameUseCaseMock.invoke(query) }
    }

    @Test
    fun `initialize should set Error state when use case fails`() = runTest(testDispatcher) {
        val query = "zapatito"
        val mockResponse = Result.failure<ItemsResponseModel>(Throwable("Error"))

        coEvery { getItemByNameUseCaseMock.invoke(query) } returns mockResponse

        viewModel.initialize()

        viewModel.searchViewState.test {
            assertEquals(SearchViewState.IdleScreen, awaitItem())
            viewModel.updateInput(query)
            assertEquals(SearchViewState.Loading, awaitItem())
            assertEquals(SearchViewState.Error, awaitItem())
        }

        coVerify(exactly = 1) { getItemByNameUseCaseMock.invoke(query) }
    }

    @Test
    fun `initialize should set IdleScreen state when input is empty`() = runTest(testDispatcher) {
        val query = ""

        viewModel.initialize()

        viewModel.searchViewState.test {
            viewModel.updateInput(query)
            assertEquals(SearchViewState.IdleScreen, awaitItem())
        }

        coVerify(exactly = 0) { getItemByNameUseCaseMock.invoke(query) }
    }

    @Test
    fun `searchFieldActivated should activate search field based on current input`() = runTest {
        viewModel.updateInput("query")
        viewModel.searchFieldActivated()

        assertEquals(
            SearchFieldState.WithInputActive,
            viewModel.searchFieldState.value
        )

        viewModel.updateInput("")
        viewModel.searchFieldActivated()

        assertEquals(
            SearchFieldState.EmptyActive,
            viewModel.searchFieldState.value
        )
    }

    @Test
    fun `clearInput should clear inputText, set EmptyActive state, and set Loading view state`() =
        runTest {
            viewModel.updateInput("query")

            viewModel.clearInput()

            assertEquals("", viewModel.inputText.value)
            assertEquals(
                SearchFieldState.EmptyActive,
                viewModel.searchFieldState.value
            )
            assertEquals(SearchViewState.Loading, viewModel.searchViewState.value)
        }

    @Test
    fun `revertToInitialState should reset view and search field states`() = runTest {
        viewModel.updateInput("query")
        viewModel.searchFieldActivated()
        viewModel.revertToInitialState()

        assertEquals("", viewModel.inputText.value)
        assertEquals(SearchFieldState.Idle, viewModel.searchFieldState.value)
        assertEquals(SearchViewState.IdleScreen, viewModel.searchViewState.value)
    }


}