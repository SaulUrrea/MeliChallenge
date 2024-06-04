package com.saulodev.melichallenge.ui.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.saulodev.melichallenge.domain.models.ItemModel
import com.saulodev.melichallenge.ui.home.screens.DetailItemScreen
import com.saulodev.melichallenge.ui.home.screens.SearchScreen
import com.saulodev.melichallenge.utils.Constants.ITEM_ARG

/**
 * Sets up the navigation for the application.
 *
 * @param navHostController The navigation controller responsible for managing navigation within the app.
 */
@Composable
fun AppNavigation(navHostController: NavHostController) {

    NavHost(
        navController = navHostController,
        startDestination = AppScreens.SearchScreen.route
    ) {

        composable(route = AppScreens.SearchScreen.route) {
            SearchScreen(
                navHostController,
                hiltViewModel()
            )
        }

        composable(route = AppScreens.DetailItemScreen.route) {
            val itemModel = it.arguments?.getParcelable<ItemModel>(ITEM_ARG)

            itemModel?.let { item ->
                DetailItemScreen(
                    item,
                    navHostController,
                    hiltViewModel()
                )
            }
        }

    }
}


/**
 * This function attempts to find a destination with the provided route within the current
 * navigation graph. If a matching destination is found, it navigates to it using the given
 * arguments, navigation options, and navigator extras.
 *
 * @param route The route of the destination to navigate to.
 * @param args Arguments to pass to the destination.
 * @param navOptions Navigation options for this navigation operation.
 * @param navigatorExtras Extras to pass to the Navigator.
 */
fun NavController.navigateWithArgs(
    route: String,
    args: Bundle,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    val nodeId = graph.findNode(route = route)?.id
    if (nodeId != null) {
        navigate(nodeId, args, navOptions, navigatorExtras)
    }
}