package com.drsync.listcharacter.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.drsync.listcharacter.data.entity.Character

@Database(
    entities = [Character::class],
    version = 1,
    exportSchema = false
)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    companion object {
        @Volatile
        private var instance: CharacterDatabase? = null
        fun getInstance(context: Context): CharacterDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    CharacterDatabase::class.java, "Characters.db"
                ).build()
            }
    }
}