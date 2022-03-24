package com.drsync.listcharacter

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.drsync.listcharacter.data.Character
import com.drsync.listcharacter.viewmodel.CharacterViewModel

@Composable
fun CharacterHomeContent(navigateToProfile: (Character) -> Unit) {
    val viewModel = CharacterViewModel()

    viewModel.getCharacter()
    val character = remember { viewModel.characterData }
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(character.value){
            CharacterListItem(character = it, navigateToProfile = navigateToProfile)
        }
//            items = character,
//            itemContent = {
//                CharacterListItem(character = it, navigateToProfile)
//            }
//        )
    }
}