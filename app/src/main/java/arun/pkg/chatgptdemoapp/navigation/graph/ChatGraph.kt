package arun.pkg.chatgptdemoapp.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import arun.pkg.chatgptdemoapp.compose.ChatGPTScreen
import arun.pkg.chatgptdemoapp.compose.ImageGenerationScreen
import arun.pkg.chatgptdemoapp.compose.MainScreen
import arun.pkg.chatgptdemoapp.navigation.Navigation

fun NavGraphBuilder.chatgptGraph(navController: NavController) {
    navigation(
        startDestination = Navigation.Route.ROUTE_MAIN_SCREEN,
        route = Navigation.Route.ROUTE_CHAT_GPT
    ) {
        mainScreen(navController)
        chatScreen()
        imagesScreen()
        ttsScreen()
    }
}
fun NavGraphBuilder.mainScreen(navController: NavController) {
    composable(
        route = Navigation.Route.ROUTE_MAIN_SCREEN
    ) {
        MainScreen(navController)
    }
}
fun NavGraphBuilder.chatScreen() {
    composable(
        route = Navigation.Route.ROUTE_CHAT_SCREEN
    ) {
        ChatGPTScreen()
    }
}

fun NavGraphBuilder.imagesScreen() {
    composable(
        route = Navigation.Route.ROUTE_IMAGES_SCREEN
    ) {
        ImageGenerationScreen()
    }
}

fun NavGraphBuilder.ttsScreen() {
    composable(
        route = Navigation.Route.ROUTE_TTS_SCREEN
    ) {
        ChatGPTScreen()
    }
}