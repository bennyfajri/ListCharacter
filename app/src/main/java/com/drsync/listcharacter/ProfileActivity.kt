package com.drsync.listcharacter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.drsync.listcharacter.data.Character
import com.drsync.listcharacter.ui.theme.ListCharacterTheme

class ProfileActivity : ComponentActivity() {

    private val character: Character by lazy {
        intent?.getParcelableExtra(CHARA_ID)!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListCharacterTheme {
                ProfileScreen(character = character)
            }
        }
    }

    companion object {
        private const val CHARA_ID = "Chara_id"
        fun newIntent(context: Context, character: Character) =
            Intent(context, ProfileActivity::class.java).apply {
                putExtra(CHARA_ID, character)
            }
    }
}