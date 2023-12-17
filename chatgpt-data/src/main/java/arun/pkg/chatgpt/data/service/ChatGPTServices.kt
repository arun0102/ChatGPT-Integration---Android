package arun.pkg.chatgpt.data.service

import arun.pkg.chatgpt.data.model.ChatGPTResponse
import arun.pkg.chatgpt.data.model.ChatMessageRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatGPTServices {
    @POST("/v1/chat/completions")
    suspend fun chatRequest(
        @Body request: ChatMessageRequest,
    ): ChatGPTResponse
}