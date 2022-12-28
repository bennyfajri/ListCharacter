package com.drsync.listcharacter

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.drsync.listcharacter.data.entity.Character
import com.drsync.listcharacter.ui.common.UiState
import com.drsync.listcharacter.viewmodel.CharacterViewModel
import com.drsync.listcharacter.viewmodel.ViewModelFactory

@Composable
fun CharacterHomeContent(
    context: Context,
    viewModel: CharacterViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context)
    ),
    navigateToProfile: (Character) -> Unit
) {

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState) {
            is UiState.Loading -> {
                viewModel.getCharacters()
            }
            is UiState.Success -> {
                LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
                    items(uiState.data) {
                        CharacterListItem(character = it, navigateToProfile = navigateToProfile)
                    }
                }
            }
            is UiState.Error -> {}
        }
    }

}