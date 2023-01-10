package com.drsync.listcharacter

import android.content.res.Resources
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.drsync.listcharacter.data.DataProvider
import com.drsync.listcharacter.ui.navigation.Screen
import com.drsync.listcharacter.ui.theme.ListCharacterTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MyCharacterAppTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController
    private lateinit var context: TestContext

    @Before
    fun setUp() {
        composeTestRule.setContent {
            ListCharacterTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                MyCharacterApp(navController = navController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_NavigationToDetailWithData() {
        composeTestRule.onNodeWithTag("CharacterList").performScrollToIndex(9)
        composeTestRule.onNodeWithText(DataProvider.characterList[9].name).performClick()
        navController.assertCurrentRouteName(Screen.DetailCharacter.route)
        composeTestRule.onNodeWithText(DataProvider.characterList[9].name).assertIsDisplayed()
    }

    @Test
    fun navHost_bottomNavigation_isWorking() {
        composeTestRule.onNodeWithStringId(R.string.menu_favorited).performClick()
        navController.assertCurrentRouteName(Screen.Favorited.route)
        composeTestRule.onNodeWithStringId(R.string.menu_profile).performClick()
        navController.assertCurrentRouteName(Screen.Profile.route)
        composeTestRule.onNodeWithStringId(R.string.menu_home).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_BackButtonOnDetailPressed_toHome() {
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.key_char_list)).performScrollToIndex(9)
        composeTestRule.onNodeWithText(DataProvider.characterList[9].name).performClick()
        navController.assertCurrentRouteName(Screen.DetailCharacter.route)
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.back)).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }
}