package com.saulodev.melichallenge.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.saulodev.melichallenge.R

/**
 * Displays the UI for the "Not Found" results state.
 * Shows a message indicating no results were found along with an illustration.
 */
@Composable
fun NotFoundResultsStateView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.not_found_results))
        Image(
            painter = painterResource(
                id = R.drawable.ic_search_not_found,
            ),
            contentDescription = "Illustration",
            modifier = Modifier.padding(50.dp),
            contentScale = ContentScale.Crop
        )
    }

}