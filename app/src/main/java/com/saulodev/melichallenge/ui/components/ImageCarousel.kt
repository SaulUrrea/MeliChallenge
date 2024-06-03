package com.saulodev.melichallenge.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.saulodev.melichallenge.domain.models.PictureModel
import com.saulodev.melichallenge.utils.calculateCurrentOffsetForPage
import kotlin.math.absoluteValue

/**
 * Composable function to display an image carousel using a HorizontalPager.
 *
 * @param pagerState The state object for managing the pager.
 * @param pictures The list of PictureModel objects to display in the carousel.
 */
@Composable
@OptIn(ExperimentalFoundationApi::class)
fun ImageCarousel(
    pagerState: PagerState,
    pictures: List<PictureModel>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier

        ) { page ->
            Card(
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset =
                            pagerState.calculateCurrentOffsetForPage(page).absoluteValue
                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale

                        }
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )

                    }
                    .fillMaxWidth(),
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray)
                ) {

                    ImageFromNetwork(
                        url = pictures[page].url,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }

    }
}