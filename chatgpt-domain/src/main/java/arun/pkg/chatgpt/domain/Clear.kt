package arun.pkg.chatgpt.domain

import javax.inject.Inject

class Clear @Inject constructor(
    private val chatGPTRepository: ChatGPTRepository
) {
    suspend fun clear() {
        return chatGPTRepository.clear()
    }
}