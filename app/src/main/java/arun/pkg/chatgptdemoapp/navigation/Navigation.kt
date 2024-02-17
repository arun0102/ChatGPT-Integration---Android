package arun.pkg.chatgptdemoapp.navigation

object Navigation {
    object Path {
        const val PATH_MAIN_SCREEN = "main_screen"
        const val PATH_CHAT_SCREEN = "chat_screen"
        const val PATH_IMAGES_SCREEN = "images_screen"
        const val PATH_TTS_SCREEN = "tts_screen"
        const val PATH_CHAT_GPT = "chat_gpt"
    }

    object Route {
        const val ROUTE_CHAT_GPT = Path.PATH_CHAT_GPT
        const val ROUTE_MAIN_SCREEN = Path.PATH_MAIN_SCREEN
        const val ROUTE_CHAT_SCREEN = Path.PATH_CHAT_SCREEN
        const val ROUTE_IMAGES_SCREEN = Path.PATH_IMAGES_SCREEN
        const val ROUTE_TTS_SCREEN = Path.PATH_TTS_SCREEN
    }
}