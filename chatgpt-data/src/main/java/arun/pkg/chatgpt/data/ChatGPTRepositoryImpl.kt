package arun.pkg.chatgpt.data

import arun.pkg.chatgpt.data.model.chat.ChatMessageRequest
import arun.pkg.chatgpt.data.model.chat.Message
import arun.pkg.chatgpt.data.model.image_generation.ImageGenerationRequest
import arun.pkg.chatgpt.data.service.ChatGPTServices
import arun.pkg.chatgpt.domain.ChatGPTRepository
import arun.pkg.chatgpt.domain.chat.ChatMessages
import arun.pkg.chatgpt.domain.image_generation.ImageResponse

class ChatGPTRepositoryImpl(
    private val chatGPTServices: ChatGPTServices
) : ChatGPTRepository {

    private val chatMessagesList = mutableListOf<Message>()

    override suspend fun sendChatMessage(searchText: String): List<ChatMessages> {
        chatMessagesList.add(
            Message(
                "user",
                searchText
            )
        )

        val result = chatGPTServices.chatRequest(
            ChatMessageRequest(
                messages = chatMessagesList
            )
        )
        return run {
            chatMessagesList.add(result.choicesList[0].message)
            chatMessagesList.toDomain()
        }

    }

    override suspend fun sendImageGenerationRequest(searchText: String): String {
        val result = chatGPTServices.imageGenerationRequest(
            ImageGenerationRequest(
                prompt = searchText
            )
        )
        return result.data[0].url
    }

    override suspend fun clear() {
        chatMessagesList.clear()
    }
}

private fun MutableList<Message>.toDomain(): List<ChatMessages> {
    val chatMessages = mutableListOf<ChatMessages>()
    forEach {
        chatMessages.add(
            ChatMessages(
                role = it.role,
                content = it.content
            )
        )
    }
    return chatMessages
}
