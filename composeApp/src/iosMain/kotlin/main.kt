import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.ComposeUIViewController
import com.khomichenko.notes.MainLifecycleOwner
import com.khomichenko.notes.RootScreen
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