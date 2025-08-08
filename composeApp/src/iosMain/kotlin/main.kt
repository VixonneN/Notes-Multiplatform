import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.ComposeUIViewController
import com.khomichenko.root.ui.MainLifecycleOwner
import com.khomichenko.root.ui.RootScreen
import com.khomichenko.root.component.RootComponent
import platform.UIKit.UIViewController

@Suppress("FunctionName", "unused")
fun MainViewController(
    rootComponent: RootComponent
): UIViewController {
    return ComposeUIViewController {
        CompositionLocalProvider(
            MainLifecycleOwner provides rootComponent
        ) {
            RootScreen(rootComponent)
        }
    }
}