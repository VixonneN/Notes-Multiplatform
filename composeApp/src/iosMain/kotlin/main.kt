import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.ComposeUIViewController
import com.khomichenko.root.ui.MainLifecycleOwner
import com.khomichenko.root.ui.RootScreen
import com.khomichenko.root.component.RootComponent
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize
import platform.UIKit.UIViewController

@Suppress("FunctionName", "unused")
fun MainViewController(
    rootComponent: RootComponent
): UIViewController {
    Firebase.initialize()
    return ComposeUIViewController {
        CompositionLocalProvider(
            MainLifecycleOwner provides rootComponent
        ) {
            RootScreen(rootComponent)
        }
    }
}