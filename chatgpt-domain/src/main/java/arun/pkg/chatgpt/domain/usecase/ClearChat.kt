package arun.pkg.chatgpt.domain.usecase

import arun.pkg.chatgpt.domain.ChatGPTRepository
import javax.inject.Inject

class ClearChat @Inject constructor(
    private val chatGPTRepository: ChatGPTRepository
) {
    suspend fun clearChat() {
        return chatGPTRepository.clearChat()
    }
}