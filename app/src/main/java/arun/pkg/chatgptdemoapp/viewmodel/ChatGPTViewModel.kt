package arun.pkg.chatgptdemoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arun.pkg.chatgpt.domain.Clear
import arun.pkg.chatgpt.domain.chat.ChatMessages
import arun.pkg.chatgpt.domain.chat.usecase.SendChatMessage
import arun.pkg.chatgpt.domain.image_generation.ImageResponse
import arun.pkg.chatgpt.domain.image_generation.usecase.SendImageGenerationRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatGPTViewModel @Inject constructor(
    private val sendChatMessage: SendChatMessage,
    private val imageGenerationRequest: SendImageGenerationRequest,
    private val clearChat: Clear
) : ViewModel() {

    private var _messagesList: MutableLiveData<List<ChatMessages>> = MutableLiveData(emptyList())
    val messagesList: LiveData<List<ChatMessages>>
        get() = _messagesList

    private var _imageSearchResult: MutableLiveData<ImageResponse?> = MutableLiveData(null)
    val imageSearchResult: LiveData<ImageResponse?>
        get() = _imageSearchResult

    fun userChatMessage(searchText: String) {
        viewModelScope.launch {
            val results = sendChatMessage.sendChatMessage(searchText)
            _messagesList.value = results
        }
    }

    fun generateImage(searchText: String, size: String) {
        viewModelScope.launch {
            val results = imageGenerationRequest.sendImageGenerationRequest(searchText, size)
            _imageSearchResult.value = results
        }
    }


    fun clearChat() {
        viewModelScope.launch {
            clearChat.clear()
            _messagesList.value = emptyList()
            _imageSearchResult.value = null
        }
    }
}