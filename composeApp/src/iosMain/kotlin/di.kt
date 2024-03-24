import com.arkivanov.decompose.ComponentContext
import com.khomichenko.notes.di.AppModule
import com.khomichenko.root.component.RootComponent
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf

fun initKoin() {
    startKoin {
        modules(AppModule)
    }
}

class RootHelper(private val componentContext: ComponentContext): KoinComponent  {
    val rootComponent: RootComponent by inject(parameters = { parametersOf(componentContext) })
}