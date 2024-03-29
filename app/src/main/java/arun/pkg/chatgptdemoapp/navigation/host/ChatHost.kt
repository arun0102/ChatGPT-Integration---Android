package arun.pkg.chatgptdemoapp.navigation.host

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import arun.pkg.chatgptdemoapp.navigation.Navigation
import arun.pkg.chatgptdemoapp.navigation.graph.chatgptGraph
import arun.pkg.chatgptdemoapp.navigation.graph.mainScreen

@Composable
fun ChatHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Navigation.Route.ROUTE_CHAT_GPT
    ) {
        chatgptGraph(navHostController)
    }
}