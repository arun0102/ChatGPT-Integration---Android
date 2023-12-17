package arun.pkg.chatgptdemoapp.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import arun.pkg.chatgptdemoapp.compose.ChatGPTScreen
import arun.pkg.chatgptdemoapp.navigation.Navigation

fun NavGraphBuilder.chatGPTScreen() {
    composable(
        route = Navigation.Route.ROUTE_CHAT_GPT_SCREEN
    ) {
        ChatGPTScreen()
    }
}