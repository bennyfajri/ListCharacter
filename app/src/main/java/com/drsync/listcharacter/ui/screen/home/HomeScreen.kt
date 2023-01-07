package com.drsync.listcharacter.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.drsync.listcharacter.R
import com.drsync.listcharacter.data.entity.Character
import com.drsync.listcharacter.di.Injection
import com.drsync.listcharacter.ui.common.UiState
import com.drsync.listcharacter.ui.components.CharacterItem
import com.drsync.listcharacter.ui.ViewModelFactory
import com.drsync.listcharacter.ui.components.SearchBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    navigateToDetail: (Int) -> Unit
) {
    val query by viewModel.query
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getCharacters()
            }
            is UiState.Success -> {
                HomeContent(
                    characters = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                    searchCharacter = viewModel::searchCharacters,
                    query = query
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    characters: List<Character>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
    searchCharacter: (String) -> Unit,
    query: String? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SearchBar(
            query = query as String,
            onValueChange = searchCharacter,
            modifier = Modifier.padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
        )
        if (characters.isEmpty()) {
            Text(
                text = stringResource(id = R.string.no_chara_found),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                modifier = modifier.testTag("CharacterList")
            ) {
                items(characters, key = { it.id }) { data ->
                    CharacterItem(
                        image = data.imageUrl,
                        name = data.name,
                        modifier = Modifier.clickable {
                            navigateToDetail(data.id)
                        }
                    )
                }
            }
        }
    }
}
