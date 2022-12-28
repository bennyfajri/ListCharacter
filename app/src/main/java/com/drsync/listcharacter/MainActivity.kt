package com.drsync.listcharacter

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.drsync.listcharacter.data.entity.Character
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyApp(navigateToProfile: (Character) -> Unit) {
    val context = LocalContext.current
    Scaffold(
        content = {
            CharacterHomeContent(context, navigateToProfile = navigateToProfile)
        }
    )
}