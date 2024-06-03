package com.saulodev.melichallenge.ui.home.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saulodev.melichallenge.domain.usecases.IGetPicturesByItemId
import com.saulodev.melichallenge.ui.home.models.DetailItemViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailItemViewModel @Inject constructor(
    private val getPicturesByItemId: IGetPicturesByItemId
) : ViewModel() {

    private val _DetailItemViewState: MutableStateFlow<DetailItemViewState> =
        MutableStateFlow(DetailItemViewState.Loading)
    val detailItemViewState: StateFlow<DetailItemViewState> = _DetailItemViewState

    /**
     * Retrieves pictures for a specified item by its ID using the provided [viewModelScope].
     * If successful, updates the [_DetailItemViewState] with the fetched pictures.
     * If no pictures are retrieved or an error occurs, updates the [_DetailItemViewState] with an error state.
     *
     * @param id The ID of the item to retrieve pictures for.
     */
    fun getPicturesForItem(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getPicturesByItemId.invoke(id)
                .onSuccess { response ->
                    if (response.pictures.isNullOrEmpty()) {
                        _DetailItemViewState.update { DetailItemViewState.Error }
                    } else {
                        _DetailItemViewState.update {
                            DetailItemViewState.PicturesResultsFetched(
                                response.pictures
                            )
                        }
                    }
                }
                .onFailure {
                    Log.e("Error", "getPicturesForItem: ", it)
                    _DetailItemViewState.update { DetailItemViewState.Error }
                }
        }
    }


}
