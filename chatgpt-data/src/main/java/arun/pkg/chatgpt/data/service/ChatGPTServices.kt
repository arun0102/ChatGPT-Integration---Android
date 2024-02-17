package arun.pkg.chatgpt.data.service

import arun.pkg.chatgpt.data.model.chat.ChatGPTResponse
import arun.pkg.chatgpt.data.model.chat.ChatMessageRequest
import arun.pkg.chatgpt.data.model.image_generation.ImageGenerationRequest
import arun.pkg.chatgpt.data.model.image_generation.ImageGenerationResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatGPTServices {
    @POST("/v1/chat/completions")
    suspend fun chatRequest(
        @Body request: ChatMessageRequest,
    ): ChatGPTResponse

    @POST("/v1/images/generations")
    suspend fun imageGenerationRequest(
        @Body request: ImageGenerationRequest,
    ): ImageGenerationResponse
}