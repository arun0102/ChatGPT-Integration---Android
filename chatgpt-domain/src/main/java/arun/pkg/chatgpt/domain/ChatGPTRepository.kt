package arun.pkg.chatgpt.domain

import arun.pkg.chatgpt.domain.chat.ChatMessages

interface ChatGPTRepository {
    suspend fun sendChatMessage(searchText: String): List<ChatMessages>

    suspend fun sendImageGenerationRequest(searchText: String): String

    suspend fun clear()
}