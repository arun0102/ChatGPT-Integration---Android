package arun.pkg.chatgptdemoapp.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import arun.pkg.chatgptdemoapp.viewmodel.ChatGPTViewModel
import coil.compose.AsyncImage

@Composable
@Preview
private fun PreviewImageGenerationScreen() {
    ImageGenerationScreen()
}

@Composable
fun ImageGenerationScreen(
    viewModel: ChatGPTViewModel = hiltViewModel()
) {
    var searchText by remember { mutableStateOf("Flower image") }
    val searchedImageUrl: String? by viewModel.imageSearchResult.observeAsState()


    Column(
        modifier = Modifier.background(
            Color.White,
            shape = RoundedCornerShape(4.dp)
        )
    ) {
        ImageSearchView(
            searchText = searchText,
            textChanged = {
                searchText = it
            }, generateImage = {
                viewModel.generateImage(it)
                searchText = ""
            }, clearChat = {
                viewModel.clearChat()
            })

        Spacer(modifier = Modifier.size(16.dp))

        ImageResult(url = searchedImageUrl)
    }
}

@Composable
fun ImageSearchView(
    searchText: String,
    textChanged: (String) -> Unit = {},
    generateImage: (String) -> Unit = {},
    clearChat: () -> Unit = {}
) {
    Column {
        TextField(modifier = Modifier.fillMaxWidth(), value = searchText, onValueChange = {
            textChanged(it)
        })
        Spacer(modifier = Modifier.padding(6.dp))

        Row {
            Button(onClick = {
                generateImage(searchText)
            }) {
                Text(text = "Send")
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Button(onClick = {
                clearChat()
            }) {
                Text(text = "Clear")
            }
        }
    }
}

@Composable
fun ImageResult(url: String?) {
    Column(modifier = Modifier.padding(4.dp)) {
        url?.let {
            AsyncImage(
                model = url,
                contentDescription = "Image from ChatGPT"
            )
        }
    }
}
