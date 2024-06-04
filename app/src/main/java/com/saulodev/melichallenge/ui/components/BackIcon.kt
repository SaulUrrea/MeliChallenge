package com.saulodev.melichallenge.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.saulodev.melichallenge.R


/**
 * Composable function that displays a back icon button.
 *
 * @param onClick The callback to be invoked when the icon is clicked.
 */
@Composable
fun BackIcon(onClick: () -> Unit) {
    IconButton(onClick = { onClick.invoke() }) {
        Icon(
            Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = stringResource(R.string.back_button),
            tint = Color.Black,
            modifier = Modifier.size(20.dp)
        )
    }
}