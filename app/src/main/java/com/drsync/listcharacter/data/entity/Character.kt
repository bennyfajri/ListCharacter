package com.drsync.listcharacter.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "characters")
data class Character(
    @field:ColumnInfo(name = "id")
    @field:PrimaryKey(autoGenerate = true)
    val id: Int,

    @field:ColumnInfo(name = "name")
    val name: String,

    @field:ColumnInfo(name = "gender")
    val gender: String,

    @field:ColumnInfo(name = "birthday")
    val birthday: String,

    @field:ColumnInfo(name = "region")
    val region: String,

    @field:ColumnInfo(name = "lore")
    val lore: String,

    @field:ColumnInfo(name = "imageUrl")
    val imageUrl: String,

    @field:ColumnInfo(name = "favorited")
    var isFavorited: Boolean
) : Parcelable