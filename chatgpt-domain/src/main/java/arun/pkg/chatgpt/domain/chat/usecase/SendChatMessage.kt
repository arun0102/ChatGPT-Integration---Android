package arun.pkg.chatgpt.domain.chat.usecase

import arun.pkg.chatgpt.domain.ChatGPTRepository
import arun.pkg.chatgpt.domain.chat.ChatMessages
import javax.inject.Inject

class SendChatMessage @Inject constructor(
    private val chatGPTRepository: ChatGPTRepository
) {
    suspend fun sendChatMessage(searchText: String): List<ChatMessages> {
        return chatGPTRepository.sendChatMessage(searchText)
    }
}