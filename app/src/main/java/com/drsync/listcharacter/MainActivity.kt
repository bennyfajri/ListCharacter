package com.drsync.listcharacter

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.drsync.listcharacter.data.Character
import com.drsync.listcharacter.ui.theme.ListCharacterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListCharacterTheme {
                MyApp{
                    startActivity(ProfileActivity.newIntent(this,it))
                }
            }
        }
    }
}

@Composable
fun MyApp(navigateToProfile: (Character) -> Unit) {
    Scaffold(
        content = {
            CharacterHomeContent(navigateToProfile = navigateToProfile)
        }
    )
}