package com.pickpoint.pickpoint.navigation

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Settings : Routes("settings")
    object Report : Routes("report")

    object RandomPicker : Routes("random_picker")

    object TeamMaker : Routes("team_maker")

    object WhatToDo : Routes("what_to_do")

    object QRCode : Routes("qr_code")
}
