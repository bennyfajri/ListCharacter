package com.drsync.listcharacter.di

import android.content.Context
import com.drsync.listcharacter.data.room.CharacterDatabase
import com.drsync.listcharacter.repository.CharacterRepository

object Injection {
    fun provideRepository(context: Context): CharacterRepository {
        val database = CharacterDatabase.getInstance(context)
        val dao = database.characterDao()
        return CharacterRepository.getInstance(dao)
    }
}