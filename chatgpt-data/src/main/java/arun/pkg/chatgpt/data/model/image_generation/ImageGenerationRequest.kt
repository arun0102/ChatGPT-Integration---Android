package arun.pkg.chatgpt.data.model.image_generation

data class ImageGenerationRequest(
    val model: String = "dall-e-3",
    val prompt: String = "",
    val n: Int = 1,
    val size: String = "1024x1024"
)