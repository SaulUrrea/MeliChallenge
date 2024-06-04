package com.saulodev.melichallenge.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saulodev.melichallenge.R

/**
 * Displays a header with a back arrow button and a title.
 *
 * @param textAlign The alignment of the title text. Default is TextAlign.Left.
 * @param textTitle The title text to display.
 * @param backIconButton The action to perform when the back button is clicked.
 */
@Composable
fun BackArrowIndex(
    textAlign: TextAlign = TextAlign.Left,
    textTitle: String,
    backIconButton: () -> Unit
) {

    BackHandler(true) {
        backIconButton()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(10.dp, 0.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { backIconButton() }, modifier = Modifier.size(30.dp)) {
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = stringResource(R.string.back_arrow),
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
            }
            Text(
                text = textTitle,
                fontSize = 22.sp,
                textAlign = textAlign,
                color = Color.Black,
                modifier = Modifier
                    .weight(1f)
                    .padding(20.dp, 0.dp, 0.dp, 0.dp),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(30.dp))
        }
    }
}