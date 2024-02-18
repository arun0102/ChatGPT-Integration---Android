package arun.pkg.chatgptdemoapp.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import arun.pkg.chatgptdemoapp.navigation.Navigation

@Composable
@Preview
private fun PreviewMainScreen() {
    MainScreenView()
}

@Composable
fun MainScreen(navController: NavController) {
    MainScreenView(
        onChatClicked = {
            navController.navigate(Navigation.Route.ROUTE_CHAT_SCREEN)
        },
        onImageGenerationClicked = {
            navController.navigate(Navigation.Route.ROUTE_IMAGES_SCREEN)
        }
    )
}

@Composable
private fun MainScreenView(
    onChatClicked: () -> Unit = {},
    onImageGenerationClicked: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                Color.White
            )
            .padding(20.dp)
    ) {
        Button(onClick = {
            onChatClicked()
        }) {
            Text(text = "Chat")
        }
        Spacer(modifier = Modifier.padding(16.dp))

        Button(onClick = {
            onImageGenerationClicked()
        }) {
            Text(text = "Generate Images")
        }
        Spacer(modifier = Modifier.padding(16.dp))

        Button(onClick = {

        }) {
            Text(text = "Text to Speech (In-progress)")
        }
        Spacer(modifier = Modifier.padding(16.dp))

        Button(onClick = {

        }) {
            Text(text = "Read Image (In-progress)")
        }
        Spacer(modifier = Modifier.padding(16.dp))
    }
}



