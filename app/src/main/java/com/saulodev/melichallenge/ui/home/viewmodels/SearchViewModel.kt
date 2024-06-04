package com.saulodev.melichallenge.ui.home.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saulodev.melichallenge.domain.usecases.IGetItemByNameUseCase
import com.saulodev.melichallenge.ui.home.models.SearchFieldState
import com.saulodev.melichallenge.ui.home.models.SearchViewState
import com.saulodev.melichallenge.utils.Constants.EMPTY_STRING
import com.saulodev.melichallenge.utils.blankOrEmpty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getItemByNameUseCase: IGetItemByNameUseCase
) : ViewModel() {

    private val _searchFieldState: MutableStateFlow<SearchFieldState> =
        MutableStateFlow(SearchFieldState.Idle)
    val searchFieldState: StateFlow<SearchFieldState> = _searchFieldState

    private val _Search_viewState: MutableStateFlow<SearchViewState> =
        MutableStateFlow(SearchViewState.IdleScreen)
    val searchViewState: StateFlow<SearchViewState> = _Search_viewState

    private val _inputText: MutableStateFlow<String> = MutableStateFlow(EMPTY_STRING)
    val inputText: StateFlow<String> = _inputText

    private val isInitialized = AtomicBoolean(false)

    /**
     * Initializes the ViewModel by observing changes to the input text and fetching search results.
     */
    @FlowPreview
    fun initialize() {
        if (isInitialized.compareAndSet(false, true)) {
            viewModelScope.launch(Dispatchers.IO) {
                inputText.debounce(500).collectLatest { input ->
                    if (input.blankOrEmpty()) {
                        _Search_viewState.update { SearchViewState.IdleScreen }
                        return@collectLatest
                    }

                    getItemByNameUseCase.invoke(input)
                        .onSuccess { response ->
                            if (response.items.isEmpty()) {
                                _Search_viewState.update { SearchViewState.NoResults }
                            } else {
                                _Search_viewState.update {
                                    SearchViewState.SearchResultsFetched(
                                        response.items
                                    )
                                }
                            }
                        }
                        .onFailure {
                            _Search_viewState.update { SearchViewState.Error }
                        }
                }
            }
        }
    }

    /**
     * Updates the input text and activates the search field.
     *
     * @param inputText The new input text.
     */
    fun updateInput(inputText: String) {
        _inputText.update { inputText }
        activateSearchField()


        if (inputText.blankOrEmpty().not()) {
            _Search_viewState.update { SearchViewState.Loading }
        }
    }

    /**
     * Activates the search field, setting the appropriate state.
     */
    fun searchFieldActivated() {
        activateSearchField()
    }

    /**
     * Clears the input text and sets the state to EmptyActive.
     */
    fun clearInput() {
        _Search_viewState.update { SearchViewState.Loading }
        _inputText.update { EMPTY_STRING }
        _searchFieldState.update { SearchFieldState.EmptyActive }
    }

    /**
     * Reverts the view and search field to their initial states.
     */
    fun revertToInitialState() {
        _Search_viewState.update { SearchViewState.IdleScreen }
        _inputText.update { EMPTY_STRING }
        _searchFieldState.update { SearchFieldState.Idle }
    }

    /**
     * Activates the search field based on the current input text.
     */
    private fun activateSearchField() {
        if (inputText.value.blankOrEmpty().not()) {
            _searchFieldState.update { SearchFieldState.WithInputActive }
        } else {
            _searchFieldState.update { SearchFieldState.EmptyActive }
        }
    }

}