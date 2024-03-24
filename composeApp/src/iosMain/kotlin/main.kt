import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.ComposeUIViewController
import com.khomichenko.notes.RootScreen
import com.khomichenko.notes.MainLifecycleOwner
import com.khomichenko.root.component.RootComponent
import org.koin.core.module.Module
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