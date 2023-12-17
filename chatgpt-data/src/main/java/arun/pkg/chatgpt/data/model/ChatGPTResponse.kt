package arun.pkg.chatgpt.data.model

import com.google.gson.annotations.SerializedName

data class ChatGPTResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("object")
    val objectType: String,
    @SerializedName("model")
    val model: String,
    @SerializedName("choices")
    val choicesList: List<Choices>,
    @SerializedName("usage")
    val usage: Usage,
)

data class Choices(
    @SerializedName("message")
    val message: Message,
    @SerializedName("logprobs")
    val logprobs: String,
    @SerializedName("finish_reason")
    val finish_reason: String,
)

data class Usage(
    @SerializedName("prompt_tokens")
    val prompt_tokens: Int,
    @SerializedName("completion_tokens")
    val completion_tokens: Int,
    @SerializedName("total_tokens")
    val total_tokens: Int,
)