package arun.pkg.chatgptdemoapp.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import arun.pkg.chatgpt.domain.image_generation.ImageResponse
import arun.pkg.chatgptdemoapp.ui.theme.ChatGPTDemoAppTheme
import arun.pkg.chatgptdemoapp.viewmodel.ChatGPTViewModel
import coil.compose.AsyncImage

@Composable
@Preview
private fun PreviewImageGenerationScreen() {
    ChatGPTDemoAppTheme {
        searchImage(
            searchText = "Flower image",
            searchedImage = ImageResponse(
                "https://d33wubrfki0l68.cloudfront.net/e3de0c234f092817b6de50227f5cf18d8846963c/29c0b/public/images/posts/whats-new-in-phoenix-1-7/cover.png",
                "This is a test description"
            ),
            itemList = listOf("1024x1024", "1024x1792", "1792x1024"),
            selectedIndex = 0,
            generateImage = {},
            clearChat = {},
            selectedSize = {}
        )
    }
}

@Composable
fun ImageGenerationScreen(
    viewModel: ChatGPTViewModel = hiltViewModel()
) {
    val searchText by remember { mutableStateOf("Flower image") }
    val searchedImage: ImageResponse? by viewModel.imageSearchResult.observeAsState()

    val itemList = listOf("1024x1024", "1024x1792", "1792x1024")
    var selectedIndex by rememberSaveable { mutableStateOf(0) }

    ChatGPTDemoAppTheme {
        searchImage(searchText, searchedImage, itemList, selectedIndex,
            generateImage = {
                viewModel.generateImage(it, itemList[selectedIndex])
            }, clearChat = {
                viewModel.clearChat()
            }, selectedSize = {
                selectedIndex = it
            })
    }
}

@Composable
private fun searchImage(
    searchText: String,
    searchedImage: ImageResponse?,
    itemList: List<String>,
    selectedIndex: Int,
    generateImage: (String) -> Unit,
    clearChat: () -> Unit,
    selectedSize: (Int) -> Unit
) {
    var searchText1 = searchText
    Column(
        modifier = Modifier.background(
            Color.White,
            shape = RoundedCornerShape(4.dp)
        )
    ) {
        ImageSearchView(
            searchText = searchText1,
            itemList,
            selectedIndex,
            textChanged = {
                searchText1 = it
            },
            generateImage = {
                generateImage(it)
                searchText1 = ""
            },
            clearChat = {
                clearChat()
            },
            selectedSize
        )

        Spacer(modifier = Modifier.size(16.dp))

        ImageResult(imageResponse = searchedImage)
    }
}

@Composable
fun ImageSearchView(
    searchText: String,
    itemList: List<String>,
    selectedIndex: Int,
    textChanged: (String) -> Unit = {},
    generateImage: (String) -> Unit = {},
    clearChat: () -> Unit = {},
    selectedSize: (Int) -> Unit
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
            Spacer(modifier = Modifier.padding(16.dp))
            DropdownList(
                itemList = itemList,
                selectedIndex = selectedIndex,
                modifier = Modifier,
                selectedSize
            )
        }
    }
}

@Composable
fun ImageResult(imageResponse: ImageResponse?) {
    Column(
        modifier = Modifier.padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        imageResponse?.url?.let {
            AsyncImage(
                model = imageResponse.url,
                contentDescription = "Image from ChatGPT"
            )
            Spacer(modifier = Modifier.padding(6.dp))
            Text(text = imageResponse.description)
        }
    }
}

@Composable
fun DropdownList(
    itemList: List<String>,
    selectedIndex: Int,
    modifier: Modifier,
    onItemClick: (Int) -> Unit
) {

    var showDropdown by rememberSaveable { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // button
        Button(
            modifier = modifier
                .clickable { showDropdown = true },
            onClick = { showDropdown = !showDropdown }
        ) {
            Text(text = itemList[selectedIndex], modifier = Modifier.padding(3.dp))
        }

        // dropdown list
        Box {
            if (showDropdown) {
                Popup(
                    alignment = Alignment.TopCenter,
                    properties = PopupProperties(
                        excludeFromSystemGesture = true,
                    ),
                    // to dismiss on click outside
                    onDismissRequest = { showDropdown = false }
                ) {

                    Column(
                        modifier = modifier
                            .heightIn(max = 90.dp)
                            .width(200.dp)
                            .verticalScroll(state = scrollState)
                            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(20.dp)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        itemList.onEachIndexed { index, item ->
                            if (index != 0) {
                                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                            }
                            Box(
                                modifier = Modifier
                                    .clickable {
                                        onItemClick(index)
                                        showDropdown = !showDropdown
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = item)
                            }
                        }

                    }
                }
            }
        }
    }
}
