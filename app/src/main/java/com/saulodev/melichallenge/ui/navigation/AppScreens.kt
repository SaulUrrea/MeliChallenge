package com.saulodev.melichallenge.ui.navigation

import com.saulodev.melichallenge.utils.Constants.DETAIL_ITEM_SCREEN
import com.saulodev.melichallenge.utils.Constants.SEARCH_SCREEN

/**
 * Represents the screens in the application using a sealed class.
 * Each screen has a corresponding route.
 */
sealed class AppScreens(val route: String) {

    data object SearchScreen : AppScreens(
        route = SEARCH_SCREEN
    )

    data object DetailItemScreen : AppScreens(
        route = DETAIL_ITEM_SCREEN
    )

}
