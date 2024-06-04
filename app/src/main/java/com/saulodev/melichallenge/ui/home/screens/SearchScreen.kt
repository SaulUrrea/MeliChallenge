package com.saulodev.melichallenge.ui.home.screens

import android.os.Bundle
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.saulodev.melichallenge.R
import com.saulodev.melichallenge.domain.models.ItemModel
import com.saulodev.melichallenge.ui.components.BackIcon
import com.saulodev.melichallenge.ui.components.ClearIcon
import com.saulodev.melichallenge.ui.components.ErrorStateView
import com.saulodev.melichallenge.ui.components.IdleScreenStateView
import com.saulodev.melichallenge.ui.components.ImageFromNetwork
import com.saulodev.melichallenge.ui.components.NotFoundResultsStateView
import com.saulodev.melichallenge.ui.components.SearchIcon
import com.saulodev.melichallenge.ui.home.models.SearchFieldState
import com.saulodev.melichallenge.ui.home.models.SearchViewState
import com.saulodev.melichallenge.ui.home.viewmodels.SearchViewModel
import com.saulodev.melichallenge.ui.navigation.AppScreens
import com.saulodev.melichallenge.ui.navigation.navigateWithArgs
import com.saulodev.melichallenge.utils.Constants.EMPTY_STRING
import com.saulodev.melichallenge.utils.Constants.ITEM_ARG
import com.saulodev.melichallenge.utils.formatCurrency
import kotlinx.coroutines.FlowPreview

@FlowPreview
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel
) {

    val viewState = viewModel.searchViewState.collectAsState().value
    val searchFieldState = viewModel.searchFieldState.collectAsState().value
    val inputText = viewModel.inputText.collectAsState().value
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(null) {
        viewModel.initialize()
    }

    SearchScreenLayout(
        searchViewState = viewState,
        searchFieldState = searchFieldState,
        inputText = inputText,
        onSearchInputChanged = { input -> viewModel.updateInput(input) },
        onSearchInputClicked = { viewModel.searchFieldActivated() },
        onClearInputClicked = { viewModel.clearInput() },
        onBackClicked = {
            keyboardController?.hide()
            viewModel.revertToInitialState()
        },
        navController = navController
    )
}

@Composable
private fun SearchScreenLayout(
    searchViewState: SearchViewState,
    searchFieldState: SearchFieldState,
    inputText: String,
    onSearchInputChanged: (String) -> Unit,
    onSearchInputClicked: () -> Unit,
    onClearInputClicked: () -> Unit,
    onBackClicked: () -> Unit = {},
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray.copy(alpha = 0.1f))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SearchHeader(searchFieldState = searchFieldState)

        SearchInputField(
            searchFieldState = searchFieldState,
            inputText = inputText,
            onClearInputClicked = onClearInputClicked,
            onSearchInputChanged = onSearchInputChanged,
            onClicked = onSearchInputClicked,
            onBackClicked = onBackClicked
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
        )

        when (searchViewState) {
            SearchViewState.IdleScreen -> {
                IdleScreenStateView(searchFieldState = searchFieldState)
            }

            SearchViewState.Error -> {
                ErrorStateView()
            }

            SearchViewState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            SearchViewState.NoResults -> {
                NotFoundResultsStateView()
            }

            is SearchViewState.SearchResultsFetched -> {
                SearchResultsList(items = searchViewState.results) {
                    val bundle = Bundle()
                    bundle.putParcelable(ITEM_ARG, it)
                    navController.navigateWithArgs(
                        route = AppScreens.DetailItemScreen.route,
                        args = bundle
                    )
                }
            }
        }

    }
}

@Composable
private fun SearchHeader(
    searchFieldState: SearchFieldState
) {

    AnimatedVisibility(searchFieldState == SearchFieldState.Idle) {
        Text(
            text = stringResource(R.string.meli_challenge),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 14.dp, top = 16.dp),
            style = TextStyle(
                fontWeight = FontWeight(700),
                fontSize = 32.sp,
            )
        )
    }
}

@Composable
private fun SearchInputField(
    searchFieldState: SearchFieldState,
    inputText: String,
    onClearInputClicked: () -> Unit,
    onSearchInputChanged: (String) -> Unit,
    onClicked: () -> Unit,
    onBackClicked: () -> Unit = {},
) {

    OutlinedTextField(
        value = inputText,
        label = { Text(stringResource(R.string.product_name), color = Color.Black) },
        onValueChange = { onSearchInputChanged(it) },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        leadingIcon = {
            when (searchFieldState) {
                SearchFieldState.Idle -> SearchIcon { onClicked.invoke() }
                SearchFieldState.EmptyActive,
                SearchFieldState.WithInputActive -> BackIcon { onBackClicked.invoke() }
            }
        },
        trailingIcon = {
            if (searchFieldState is SearchFieldState.WithInputActive) {
                ClearIcon { onClearInputClicked.invoke() }
            } else {
                null
            }
        },
        interactionSource = remember { MutableInteractionSource() }.also { interactionSource ->
            LaunchedEffect(key1 = interactionSource) {
                interactionSource.interactions.collect { interaction ->
                    if (interaction is PressInteraction.Release) {
                        onClicked.invoke()
                    }
                }
            }
        },
    )
}

@Composable
private fun SearchResultsList(items: List<ItemModel>, onItemClicked: (ItemModel) -> Unit) {
    LazyColumn {
        itemsIndexed(items = items) { index, searchResult ->
            Column(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onItemClicked.invoke(searchResult)
                }
            ) {
                Spacer(
                    modifier = Modifier.height(height = if (index == 0) 16.dp else 4.dp)
                )

                ItemResult(searchResult = searchResult)


                HorizontalDivider()

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun ItemResult(searchResult: ItemModel) {
    Row {
        Box(
            modifier = Modifier
                .size(110.dp, 110.dp)
                .padding(all = 10.dp),
        ) {
            ImageFromNetwork(
                url = searchResult.thumbnail,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp))
            )
        }
        Column(
            modifier = Modifier
                .padding(all = 15.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            searchResult.title?.let {
                Text(
                    text = it,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            searchResult.locationModel?.addressLine?.takeIf { it.isNotEmpty() }?.let {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Filled.LocationOn,
                        contentDescription = stringResource(R.string.location),
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = it,
                        modifier = Modifier.padding(horizontal = 5.dp),
                        fontSize = 12.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Outlined.ShoppingCart,
                    contentDescription = stringResource(R.string.shoppingcart),
                    modifier = Modifier.size(15.dp)
                )
                Text(
                    text = searchResult.price?.formatCurrency() ?: EMPTY_STRING,
                    modifier = Modifier.padding(horizontal = 5.dp),
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
            }


        }
    }
}

