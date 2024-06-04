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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saulodev.melichallenge.R
import com.saulodev.melichallenge.ui.home.models.SearchFieldState

/**
 * Displays the UI for the Idle Screen state.
 * If the search field is in the Idle state, content is vertically centered; otherwise, it's aligned to the top.
 */
@Composable
fun IdleScreenStateView(searchFieldState: SearchFieldState) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = if (searchFieldState == SearchFieldState.Idle) {
            Arrangement.Center
        } else {
            Arrangement.Top
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_boxes_without_search),
            contentDescription = stringResource(R.string.illustration),
            modifier = Modifier.padding(20.dp)
        )
        Text(
            text = stringResource(R.string.enter_product_name),
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontSize = 16.sp,
            modifier = Modifier.padding(8.dp)
        )
    }

}