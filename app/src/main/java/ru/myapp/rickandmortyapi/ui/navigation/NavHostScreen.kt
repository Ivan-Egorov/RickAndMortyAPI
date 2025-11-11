package ru.myapp.rickandmortyapi.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ru.myapp.rickandmortyapi.ui.screens.details.DetailsScreen
import ru.myapp.rickandmortyapi.ui.screens.search.SearchScreen

@Composable
fun NavHostScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavScreen.Search) {
        composable<NavScreen.Search> { SearchScreen(navController) }

        composable<NavScreen.Details> { backStackEntry ->
            val entry = backStackEntry.toRoute<NavScreen.Details>()
            DetailsScreen(navController, entry.id)
        }
    }
}