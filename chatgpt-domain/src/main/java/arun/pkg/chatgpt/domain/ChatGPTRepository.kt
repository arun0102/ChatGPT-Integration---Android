package arun.pkg.chatgpt.domain

import arun.pkg.chatgpt.domain.chat.ChatMessages
import arun.pkg.chatgpt.domain.image_generation.ImageResponse

interface ChatGPTRepository {
    suspend fun sendChatMessage(searchText: String): List<ChatMessages>

    suspend fun sendImageGenerationRequest(searchText: String, size: String): ImageResponse

    suspend fun clear()
}