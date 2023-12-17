package arun.pkg.chatgptdemoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arun.pkg.chatgpt.domain.ChatMessages
import arun.pkg.chatgpt.domain.usecase.ClearChat
import arun.pkg.chatgpt.domain.usecase.SendChatMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatGPTViewModel @Inject constructor(
    private val sendChatMessage: SendChatMessage,
    private val clearChat: ClearChat
) : ViewModel() {

    private var _messagesList: MutableLiveData<List<ChatMessages>> = MutableLiveData(emptyList())
    val messagesList: LiveData<List<ChatMessages>>
        get() = _messagesList

    fun search(searchText: String) {
        viewModelScope.launch {
            val results = sendChatMessage.sendChatMessage(searchText)
            _messagesList.value = results
        }
    }

    fun clearChat() {
        viewModelScope.launch {
            clearChat.clearChat()
            _messagesList.value = emptyList()
        }
    }
}