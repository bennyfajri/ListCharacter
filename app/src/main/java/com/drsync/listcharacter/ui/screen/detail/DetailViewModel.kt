package com.drsync.listcharacter.ui.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drsync.listcharacter.data.entity.Character
import com.drsync.listcharacter.repository.CharacterRepository
import com.drsync.listcharacter.ui.common.UiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: CharacterRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Character>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Character>>
        get() = _uiState

    private var _isFavorited = MutableLiveData<Boolean>()
    val isFavorited : LiveData<Boolean> get() = _isFavorited

    fun getCharacterById(charId: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getCharacterById(charId))
        }
    }

    fun addToFavorite(charId: Int) {
        viewModelScope.launch {
            val character = repository.getCharacterById(charId)
            if (character.isFavorited) {
                repository.setFavoritedCharacter(character, false)
            } else {
                repository.setFavoritedCharacter(character, true)
            }
            isCharacterFavorited(character.name)
        }
    }

    fun isCharacterFavorited(name: String) {
        viewModelScope.launch {
            _isFavorited.value = repository.isCharacterFavorited(name)
        }
    }
}