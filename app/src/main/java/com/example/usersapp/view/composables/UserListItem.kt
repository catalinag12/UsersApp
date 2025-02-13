package com.example.usersapp.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.usersapp.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun UserListItem(
    imageUrl: String,
    fullName: String,
    ageAndCountry: String,
    timestamp: String,
    showDetailsIcon: Boolean = false,
) {
    val isPreview = LocalInspectionMode.current
    var isFavoriteSelected by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.padding(top = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            GlideImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp),
                model = if (isPreview) R.drawable.ic_launcher_background else imageUrl,
                contentDescription = "user image",
                loading = placeholder(R.drawable.ic_launcher_background),
            )
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = fullName)
                Text(text = ageAndCountry)
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.End,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (showDetailsIcon)
                        Image(
                            painter = painterResource(id = R.drawable.ic_attachment),
                            alpha = 0.3f,
                            contentDescription = ""
                        )
                    Text(text = timestamp)
                }

                IconButton(
                    onClick = {
                        isFavoriteSelected = !isFavoriteSelected
                    },
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = "favorite",
                        tint = if (isFavoriteSelected) Color.Red else Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
        Divider(
            color = Color.LightGray,
            thickness = 1.dp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserListItemPreview() {
    UserListItem(
        imageUrl = "example",
        fullName = "Scott Ernest",
        ageAndCountry = "27 years old from US",
        timestamp = "11:20",
        showDetailsIcon = true
    )
}
