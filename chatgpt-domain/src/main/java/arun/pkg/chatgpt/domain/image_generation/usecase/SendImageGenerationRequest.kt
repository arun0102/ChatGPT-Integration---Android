package arun.pkg.chatgpt.domain.image_generation.usecase

import arun.pkg.chatgpt.domain.ChatGPTRepository
import javax.inject.Inject

class SendImageGenerationRequest @Inject constructor(
    private val chatGPTRepository: ChatGPTRepository
) {
    suspend fun sendImageGenerationRequest(searchText: String): String {
        return chatGPTRepository.sendImageGenerationRequest(searchText)
    }
}