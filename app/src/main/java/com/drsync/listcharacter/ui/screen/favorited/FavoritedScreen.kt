package com.drsync.listcharacter.ui.screen.favorited

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.drsync.listcharacter.data.entity.Character
import com.drsync.listcharacter.di.Injection
import com.drsync.listcharacter.ui.ViewModelFactory
import com.drsync.listcharacter.ui.common.UiState
import com.drsync.listcharacter.ui.components.CharacterItem
import com.drsync.listcharacter.ui.components.SearchBar
import com.drsync.listcharacter.ui.screen.home.HomeContent

@Composable
fun FavoritedScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    navigateToDetail: (Int) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getFavoritedCharacter()
            }
            is UiState.Success -> {
                FavoriteContent(
                    characters = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun FavoriteContent(
    characters: List<Character>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = modifier.testTag("CharacterList")
    ) {
        items(characters) { data ->
            CharacterItem(
                image = data.imageUrl, name = data.name,
                modifier = Modifier.clickable {
                    navigateToDetail(data.id)
                }
            )
        }
    }
}