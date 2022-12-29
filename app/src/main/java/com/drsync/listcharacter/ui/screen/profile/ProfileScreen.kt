package com.drsync.listcharacter.ui.screen.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.drsync.listcharacter.R
import com.drsync.listcharacter.util.Constants.PROFILE_EMAIL
import com.drsync.listcharacter.util.Constants.PROFILE_IMAGE
import com.drsync.listcharacter.util.Constants.PROFILE_NAME

@Composable
fun ProfileScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp).fillMaxSize()
    ) {
        AsyncImage(
            model = PROFILE_IMAGE,
            contentDescription = stringResource(R.string.menu_profile),
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
        )
        Text(
            text = PROFILE_NAME,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Text(
            text = PROFILE_EMAIL,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}