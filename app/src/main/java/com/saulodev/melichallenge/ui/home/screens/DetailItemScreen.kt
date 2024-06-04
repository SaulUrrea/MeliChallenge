package com.saulodev.melichallenge.ui.home.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.saulodev.melichallenge.R
import com.saulodev.melichallenge.domain.models.ItemModel
import com.saulodev.melichallenge.domain.models.PictureModel
import com.saulodev.melichallenge.ui.components.AttributeList
import com.saulodev.melichallenge.ui.components.BackArrowIndex
import com.saulodev.melichallenge.ui.components.ErrorStateView
import com.saulodev.melichallenge.ui.components.ImageCarousel
import com.saulodev.melichallenge.ui.components.PageIndicator
import com.saulodev.melichallenge.ui.home.models.DetailItemViewState
import com.saulodev.melichallenge.ui.home.viewmodels.DetailItemViewModel
import com.saulodev.melichallenge.ui.navigation.AppScreens
import com.saulodev.melichallenge.ui.theme.DarkGray
import com.saulodev.melichallenge.utils.Constants.EMPTY_STRING
import com.saulodev.melichallenge.utils.formatCurrency


@Composable
fun DetailItemScreen(
    item: ItemModel,
    navController: NavController,
    viewModel: DetailItemViewModel
) {

    val viewState = viewModel.detailItemViewState.collectAsState().value
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getPicturesForItem(item.id ?: EMPTY_STRING)
    }

    DetailItemScreenLayout(
        item = item,
        navController = navController,
        scrollState = scrollState,
        context = context,
        viewState = viewState
    )
}

@Composable
fun DetailItemScreenLayout(
    item: ItemModel,
    navController: NavController,
    scrollState: ScrollState,
    context: Context,
    viewState: DetailItemViewState
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {

        BackArrowIndex(
            textTitle = stringResource(R.string.detail_product),
            textAlign = TextAlign.Center,
        ) { navController.popBackStack() }

        when (viewState) {

            DetailItemViewState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            DetailItemViewState.Error -> ErrorStateView()

            is DetailItemViewState.PicturesResultsFetched -> DetailItemBody(
                scrollState,
                item,
                viewState,
                context
            )

        }

    }
}

@Composable
private fun DetailItemBody(
    scrollState: ScrollState,
    item: ItemModel,
    viewState: DetailItemViewState.PicturesResultsFetched,
    context: Context
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp)
    ) {

        ItemHeaderWithImages(
            item,
            viewState.results,
            context
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )

        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

        AttributeList(item.attributeModels)

        SellerInfo(item)

        LocationInfo(item)

        SellerContactInfo(item, context)

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )

    }
}

@Composable
private fun SellerContactInfo(
    item: ItemModel,
    context: Context
) {
    item.sellerContactModel?.let { contact ->

        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                painterResource(id = R.drawable.ic_info),
                modifier = Modifier.padding(bottom = 8.dp),
                contentDescription = null,
                tint = Color.Black
            )

            Text(
                text = stringResource(R.string.seller_info),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        contact.webpage?.takeIf { it.isNotEmpty() }?.let { webUrl ->
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = null,
                    tint = Color.Black
                )
                Row {
                    Text(
                        text = stringResource(R.string.web_page),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = AnnotatedString(webUrl),
                        color = Color.Blue,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .clickable {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(webUrl))
                                context.startActivity(intent)
                            }
                    )
                }
            }
        }

        contact.email?.takeIf { it.isNotEmpty() }?.let {
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    Icons.Filled.Email,
                    contentDescription = null,
                    tint = Color.Black
                )
                Text(
                    text = stringResource(R.string.email, it),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }

        contact.phone?.takeIf { it.isNotEmpty() }?.let {
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    Icons.Filled.Phone,
                    contentDescription = null,
                    tint = Color.Black
                )
                Text(
                    text = stringResource(R.string.phone, it),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }

    }
}

@Composable
private fun LocationInfo(item: ItemModel) {
    item.locationModel?.let { location ->

        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                Icons.Filled.LocationOn,
                modifier = Modifier.padding(bottom = 8.dp),
                contentDescription = null,
                tint = Color.Black
            )

            Text(
                text = stringResource(R.string.location_item),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        location.addressLine?.let {
            Text(
                text = it,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        location.cityModel?.let { city ->
            Text(
                text = city.name ?: EMPTY_STRING,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        location.countryModel?.let { country ->
            Text(
                text = country.name ?: EMPTY_STRING,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }

    }
}

@Composable
private fun SellerInfo(item: ItemModel) {
    item.sellerModel?.let { seller ->
        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                painterResource(id = R.drawable.ic_sell),
                modifier = Modifier.padding(bottom = 8.dp),
                contentDescription = null,
                tint = Color.Black
            )
            Text(
                text = stringResource(R.string.seller),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        seller.nickname?.let { Text(text = it) }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ItemHeaderWithImages(
    item: ItemModel,
    pictures: List<PictureModel>,
    context: Context,
) {

    val pagerState = rememberPagerState(
        pageCount = { pictures.size },
        initialPage = 0
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        ImageCarousel(pagerState, pictures)

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            PageIndicator(
                pageCount = pagerState.pageCount,
                currentPage = pagerState.currentPage,
            )
        }

        Text(
            text = item.title ?: EMPTY_STRING,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = item.price?.formatCurrency() ?: EMPTY_STRING,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )

        item.shippingModel?.let { shipping ->

            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_package),
                    contentDescription = null,
                    tint = if (shipping.freeShipping == true) DarkGray else Color.Black
                )
                Text(
                    text = if (shipping.freeShipping == true) {
                        stringResource(R.string.free_shipping)
                    } else {
                        stringResource(R.string.shipping_with_cost)
                    },
                    color = if (shipping.freeShipping == true) DarkGray else Color.Black,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }


        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.permalink))
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(Color.Blue),
            shape = RoundedCornerShape(8.dp),
        ) {
            Text(text = stringResource(R.string.web_site), color = Color.White)
        }

    }
}
