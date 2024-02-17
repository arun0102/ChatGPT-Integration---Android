package arun.pkg.chatgpt.data.model.image_generation

import com.google.gson.annotations.SerializedName

data class ImageGenerationResponse(
    @SerializedName("data")
    val data: List<Data>,
)

data class Data(
    @SerializedName("url")
    val url: String,
)