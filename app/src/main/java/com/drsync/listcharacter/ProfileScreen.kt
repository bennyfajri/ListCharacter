package com.drsync.listcharacter

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.drsync.listcharacter.data.entity.Character

@Composable
fun ProfileScreen(character: Character) {
    val scrollstate = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollstate)
                ) {
                    ProfileHeader(
                        character = character,
                        containerHeight = this@BoxWithConstraints.maxHeight
                    )
                    ProfileContent(
                        character = character,
                        containerHeight = this@BoxWithConstraints.maxHeight
                    )
                }
            }
        }
    }
}

@Composable
private fun ProfileHeader(
    character: Character,
    containerHeight: Dp
) {
    AsyncImage(
        model = character.imageUrl,
        contentDescription = character.name,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = containerHeight/2),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun ProfileContent(character: Character, containerHeight: Dp) {
    Column {
        Name(character)
        ProfileProperty(laber = stringResource(id = R.string.birthday), value = character.birthday)
        ProfileProperty(laber = stringResource(id = R.string.gender), value = character.gender)
        ProfileProperty(laber = stringResource(id = R.string.region), value = character.region)
        ProfileProperty(laber = stringResource(id = R.string.lore), value = character.lore)

        Spacer(modifier = Modifier.height(containerHeight - 320.dp))
    }
}

@Composable
private fun Name(character: Character) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = character.name,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ProfileProperty(laber: String, value: String) {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Divider()
        Text(
            text = laber,
            modifier = Modifier.height(24.dp),
            style = MaterialTheme.typography.caption
        )
        Text(
            text = value,
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Visible
        )
    }
}