package arun.pkg.chatgpt.domain

interface ChatGPTRepository {
    suspend fun sendChatMessage(searchText: String): List<ChatMessages>

    suspend fun clearChat()
}