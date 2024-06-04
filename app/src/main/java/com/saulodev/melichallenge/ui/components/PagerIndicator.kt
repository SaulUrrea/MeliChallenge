package com.saulodev.melichallenge.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Composable function to display a page indicator showing the current
 * page number and total page count.
 */
@Composable
fun PageIndicator(
    pageCount: Int,
    currentPage: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "${currentPage + 1}/$pageCount",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}