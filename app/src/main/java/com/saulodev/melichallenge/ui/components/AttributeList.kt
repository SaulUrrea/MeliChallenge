package com.saulodev.melichallenge.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.saulodev.melichallenge.R
import com.saulodev.melichallenge.domain.models.AttributeModel

/**
 * Displays a list of attributes with a "See More"/"See Less" toggle functionality.
 *
 * @param attributes A list of attributes to display. If null, nothing is displayed.
 * @param initialLimit The initial number of attributes to display before showing the "See More" option. Default is 4.
 */
@Composable
fun AttributeList(attributes: List<AttributeModel>?, initialLimit: Int = 4) {
    attributes?.let { attributeList ->
        var isExpanded by remember { mutableStateOf(false) }
        val displayedAttributes =
            if (isExpanded) attributeList else attributeList.take(initialLimit)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                painterResource(id = R.drawable.ic_description),
                modifier = Modifier.padding(bottom = 8.dp),
                contentDescription = null,
                tint = Color.Black
            )
            Text(
                text = stringResource(R.string.characteristics),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Column {
            for (attribute in displayedAttributes) {
                val name = attribute.name
                val valueName = attribute.valueName

                if (!name.isNullOrEmpty() && !valueName.isNullOrEmpty()) {
                    Text(
                        text = "$name: $valueName",
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }

            if (attributeList.size > initialLimit) {
                Row(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .clickable { isExpanded = !isExpanded },
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = stringResource(
                            R.string.see_more_icon
                        ),
                        tint = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = if (isExpanded) {
                            stringResource(R.string.see_less)
                        } else {
                            stringResource(R.string.see_more)
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary,
                    )
                }
            }
        }
    }
}
