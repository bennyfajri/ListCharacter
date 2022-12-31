package com.drsync.listcharacter

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.drsync.listcharacter.ui.navigation.NavigationItem
import com.drsync.listcharacter.ui.navigation.Screen
import com.drsync.listcharacter.ui.screen.detail.DetailScreen
import com.drsync.listcharacter.ui.screen.favorited.FavoritedScreen
import com.drsync.listcharacter.ui.screen.home.HomeScreen
import com.drsync.listcharacter.ui.screen.profile.ProfileScreen
import com.drsync.listcharacter.ui.theme.ListCharacterTheme

@Composable
fun MyCharacterApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailCharacter.route) {
                BottomBar(navController = navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { charId ->
                        navController.navigate(Screen.DetailCharacter.createRoute(charId))
                    }
                )
            }
            composable(Screen.Favorited.route) {
                FavoritedScreen(
                    navigateToDetail = { charId ->
                        navController.navigate(Screen.DetailCharacter.createRoute(charId))
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(
                route = Screen.DetailCharacter.route,
                arguments = listOf(navArgument("charId") {
                    type = NavType.IntType
                })
            ) {
                val id = it.arguments?.getInt("charId") ?: 1
                DetailScreen(
                    characterId = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigatioItem = listOf(
            NavigationItem(
                title = stringResource(id = R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_favorited),
                icon = Icons.Default.Favorite,
                screen = Screen.Favorited
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_profile),
                icon = Icons.Default.Person,
                screen = Screen.Profile
            )
        )

        BottomNavigation {
            navigatioItem.map { item ->
                BottomNavigationItem(
                    selected = currentRoute == item.screen.route,
                    label = { Text(text = item.title) },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    ListCharacterTheme {
        MyCharacterApp()
    }
}