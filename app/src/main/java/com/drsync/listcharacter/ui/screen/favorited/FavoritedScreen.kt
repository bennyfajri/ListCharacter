package com.drsync.listcharacter.ui.screen.favorited

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.drsync.listcharacter.di.Injection
import com.drsync.listcharacter.ui.ViewModelFactory
import com.drsync.listcharacter.ui.common.UiState
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
                HomeContent(
                    characters = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {}
        }
    }
}