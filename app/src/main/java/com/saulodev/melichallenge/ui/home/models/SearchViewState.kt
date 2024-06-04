package com.saulodev.melichallenge.ui.home.models

import com.saulodev.melichallenge.domain.models.ItemModel

/**
 * Represents the different states of the view in the UI.
 */
sealed interface SearchViewState {
    /**
     * Represents the idle state of the screen.
     */
    data object IdleScreen : SearchViewState

    /**
     * Represents the loading state of the screen.
     */
    data object Loading : SearchViewState

    /**
     * Represents the error state of the screen.
     */
    data object Error : SearchViewState

    /**
     * Represents the state when no results are found.
     */
    data object NoResults : SearchViewState

    /**
     * Represents the state when search results are fetched successfully.
     *
     * @property results The list of fetched search results.
     */
    data class SearchResultsFetched(val results: List<ItemModel>) : SearchViewState
}

/**
 * Represents the different states of the search field in the UI.
 */
sealed interface SearchFieldState {
    /**
     * Represents the idle state of the search field.
     */
    data object Idle : SearchFieldState

    /**
     * Represents the state when the search field is active but empty.
     */
    data object EmptyActive : SearchFieldState

    /**
     * Represents the state when the search field is active with input.
     */
    data object WithInputActive : SearchFieldState
}

