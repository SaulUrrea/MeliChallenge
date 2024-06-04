package com.saulodev.melichallenge.ui.home.models

import com.saulodev.melichallenge.domain.models.PictureModel

/**
 * Represents the state of the detail item view through a sealed interface.
 */
sealed interface DetailItemViewState {

    /**
     * Represents the loading state of the detail item view.
     */
    data object Loading : DetailItemViewState

    /**
     * Represents the error state of the detail item view.
     */
    data object Error : DetailItemViewState

    /**
     * Represents the state when picture results are fetched successfully for the detail item view.
     *
     * @property results The list of picture models.
     */
    data class PicturesResultsFetched(val results: List<PictureModel>) : DetailItemViewState
}
