package com.drsync.listcharacter

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.drsync.listcharacter.data.Character
import com.drsync.listcharacter.data.DataProvider

@Composable
fun CharacterHomeContent(navigateToProfile: (Character) -> Unit) {
    val character = remember { DataProvider.characterList }
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(
            items = character,
            itemContent = {
                CharacterListItem(character = it, navigateToProfile)
            }
        )
    }
}