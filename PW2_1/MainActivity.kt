package com.example.pw2_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pw2_1.screens.CompensationDetailsScreen
import com.example.pw2_1.screens.MainMenuScreen
import com.example.pw2_1.screens.ReactivePowerScreen
import com.example.pw2_1.screens.UserInfoScreen
import com.example.pw2_1.ui.theme.PW2_1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PW2_1Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.MainMenu.route
                    ) {
                        composable(Screen.MainMenu.route) {
                            MainMenuScreen(navController)
                        }
                        composable(Screen.UserInfo.route) {
                            UserInfoScreen(navController)
                        }
                        composable(
                            route = "${Screen.ReactivePower.route}/{operatorName}",
                            arguments = listOf(navArgument("operatorName") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val operatorName = backStackEntry.arguments?.getString("operatorName") ?: "Черговий"
                            ReactivePowerScreen(navController, operatorName)
                        }
                        composable(
                            route = "${Screen.CompensationDetails.route}/{stepsCount}",
                            arguments = listOf(navArgument("stepsCount") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val stepsCount = backStackEntry.arguments?.getInt("stepsCount") ?: 0
                            CompensationDetailsScreen(navController, stepsCount)
                        }
                    }
                }
            }
        }
    }
}