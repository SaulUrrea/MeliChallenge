package com.saulodev.melichallenge.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.saulodev.melichallenge.R

/**
 * Composable function that displays an image fetched from a network URL.
 *
 * @param url The URL of the image to be displayed.
 */
@Composable
fun ImageFromNetwork(url: String?, modifier: Modifier) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .decoderFactory(SvgDecoder.Factory())
            .build(),
        contentDescription = stringResource(R.string.image_from_network),
        placeholder = painterResource(id = R.drawable.image_empty_state),
        error = painterResource(id = R.drawable.image_load_failed),
        contentScale = ContentScale.Crop
    )
}


