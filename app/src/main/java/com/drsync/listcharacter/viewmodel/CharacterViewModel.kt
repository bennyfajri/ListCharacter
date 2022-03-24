package com.drsync.listcharacter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drsync.listcharacter.repository.Repository
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {
    private val repository = Repository()

    val characterData = repository.listCharacter

    fun getCharacter(){
        viewModelScope.launch {
            repository.getCharacter()
        }
    }
}