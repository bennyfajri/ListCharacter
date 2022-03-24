package com.drsync.listcharacter.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val sex: String,
    val birthday: String,
    val region: String,
    val lore: String,
    val image: String
): Parcelable