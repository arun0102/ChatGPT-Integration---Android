package arun.pkg.chatgptdemoapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.compose.rememberNavController
import arun.pkg.chatgptdemoapp.navigation.host.ChatHost
import arun.pkg.chatgptdemoapp.ui.theme.ChatGPTDemoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatGPTFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = ComposeView(
        context = requireContext()
    ).apply {
        setContent {
            ChatGPTDemoAppTheme {
                ChatHost(navHostController = rememberNavController())
            }
        }
    }
}