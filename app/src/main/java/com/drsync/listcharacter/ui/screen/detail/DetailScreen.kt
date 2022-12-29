package com.drsync.listcharacter.ui.screen.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.drsync.listcharacter.R
import com.drsync.listcharacter.di.Injection
import com.drsync.listcharacter.ui.ViewModelFactory
import com.drsync.listcharacter.ui.common.UiState
import com.drsync.listcharacter.ui.components.ProfileProperty
import com.drsync.listcharacter.ui.theme.ListCharacterTheme

@Composable
fun DetailScreen(
    characterId: Int,
    viewModel: DetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository(LocalContext.current)
        )
    ),
    navigateBack: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getCharacterById(characterId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    imageUrl = data.imageUrl,
                    name = data.name,
                    birthday = data.birthday,
                    gender = data.gender,
                    region = data.region,
                    lore = data.lore
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
private fun DetailContent(
    imageUrl: String,
    name: String,
    birthday: String,
    gender: String,
    region: String,
    lore: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = name,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(1f / 3),
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopStart
        )
        Text(
            text = name,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        ProfileProperty(label = stringResource(id = R.string.birthday), value = birthday)
        ProfileProperty(label = stringResource(id = R.string.gender), value = gender)
        ProfileProperty(label = stringResource(id = R.string.region), value = region)
        ProfileProperty(label = stringResource(id = R.string.lore), value = lore)
        Spacer(modifier = Modifier.height(320.dp))
    }
}

@Preview
@Composable
fun DetailScreenPrev() {
    ListCharacterTheme {
        DetailContent(
            imageUrl = "",
            name = "Eula",
            birthday = "Oct 25th",
            gender = "Female",
            region = "Mondstadt",
            lore = "lorem ipsum"
        )
    }
}