package com.drsync.listcharacter.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drsync.listcharacter.data.entity.Character
import com.drsync.listcharacter.repository.CharacterRepository
import com.drsync.listcharacter.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: CharacterRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Character>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Character>>
        get() = _uiState

    fun getCharacterById(charId: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getCharacterById(charId))
        }
    }

    fun addToFavorite(character: Character, favoriteState: Boolean) {
        viewModelScope.launch {
            repository.setFavoritedCharacter(character, favoriteState)
        }
    }

    fun isCharacterFavorited(name: String, isFavorited: (Boolean) -> Unit) {
        viewModelScope.launch {
            isFavorited(repository.isCharacterFavorited(name))
        }
    }
}