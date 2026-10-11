package com.example.pw2_1

sealed class Screen(val route: String) {
    object MainMenu : Screen("main_menu")
    object UserInfo : Screen("user_info")
    object ReactivePower : Screen("reactive_power")
    object CompensationDetails : Screen("compensation_details")
}