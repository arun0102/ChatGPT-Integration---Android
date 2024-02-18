package arun.pkg.chatgpt.domain.image_generation.usecase

import arun.pkg.chatgpt.domain.ChatGPTRepository
import arun.pkg.chatgpt.domain.image_generation.ImageResponse
import javax.inject.Inject

class SendImageGenerationRequest @Inject constructor(
    private val chatGPTRepository: ChatGPTRepository
) {
    suspend fun sendImageGenerationRequest(searchText: String, size: String): ImageResponse {
        return chatGPTRepository.sendImageGenerationRequest(searchText, size)
    }
}