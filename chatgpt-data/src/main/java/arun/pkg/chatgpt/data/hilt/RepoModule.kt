package arun.pkg.chatgpt.data.hilt

import arun.pkg.chatgpt.data.ChatGPTRepositoryImpl
import arun.pkg.chatgpt.data.service.ChatGPTServices
import arun.pkg.chatgpt.data.service.TokenInterceptor
import arun.pkg.chatgpt.domain.ChatGPTRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepoModule {
    @Provides
    @Singleton
    internal fun provideChatGPTRepository(
        chatGPTServices: ChatGPTServices
    ): ChatGPTRepository {
        return ChatGPTRepositoryImpl(chatGPTServices)
    }

    @Provides
    @Singleton
    internal fun provideChatGPTServices(): ChatGPTServices {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val tokenInterceptor = TokenInterceptor()

        val client = OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()


        val builder = Retrofit.Builder()
            .baseUrl("https://api.openai.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client).build()
        return builder.create(ChatGPTServices::class.java)
    }
}