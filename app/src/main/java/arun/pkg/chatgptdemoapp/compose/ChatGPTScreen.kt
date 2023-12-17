package arun.pkg.chatgptdemoapp.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import arun.pkg.chatgpt.domain.ChatMessages
import arun.pkg.chatgptdemoapp.viewmodel.ChatGPTViewModel

@Composable
fun ChatGPTScreen(
    viewModel: ChatGPTViewModel = hiltViewModel()
) {
    var searchText by remember { mutableStateOf("Hello") }
    val chatList: List<ChatMessages> by viewModel.messagesList.observeAsState(emptyList())

    Column {
        InputView(
            searchText = searchText,
            textChanged = {
                searchText = it
            }, sendMessage = {
                viewModel.search(it)
                searchText = ""
            }, clearChat = {
                viewModel.clearChat()
            })

        Spacer(modifier = Modifier.size(16.dp))

        MessageList(messages = chatList)
    }
}

@Preview
@Composable
fun PreviewInputView() {
    val messages = mutableListOf<ChatMessages>()
    messages.add(ChatMessages("user", "Hello"))
    messages.add(ChatMessages("assistant", "How can I help?"))
    MessageList(messages)
}

@Composable
fun InputView(
    searchText: String,
    textChanged: (String) -> Unit = {},
    sendMessage: (String) -> Unit = {},
    clearChat: () -> Unit = {}
) {
    Column {
        TextField(modifier = Modifier.fillMaxWidth(), value = searchText, onValueChange = {
            textChanged(it)
        })
        Spacer(modifier = Modifier.padding(6.dp))

        Row {
            Button(onClick = {
                sendMessage(searchText)
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
fun MessageList(messages: List<ChatMessages>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        items(messages) { message ->
            MessageRow(message = message)
        }
    }
}

@Composable
fun MessageRow(message: ChatMessages) {
    Column(modifier = Modifier.padding(4.dp)) {
        if (message.role == "user")
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                Text(text = message.content)
            } else {
            Text(text = message.content)
        }
    }
}
