package com.khomichenko.notes

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import co.touchlab.kermit.loggerConfigInit
import com.arkivanov.decompose.defaultComponentContext
import com.khomichenko.notes.di.AppModule
import com.khomichenko.root.component.RootComponent
import com.khomichenko.root.ui.MainLifecycleOwner
import com.khomichenko.root.ui.RootScreen
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf

class AndroidApp : Application() {

    companion object {
        lateinit var INSTANCE: AndroidApp
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        loggerConfigInit()

        startKoin {
            androidContext(this@AndroidApp)
            modules(AppModule)
        }
    }
}

class AppActivity : ComponentActivity() {

    private val rootComponent: RootComponent by inject(parameters = {
        parametersOf(
            defaultComponentContext()
        )
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            CompositionLocalProvider(
                MainLifecycleOwner provides rootComponent
            ) {

                RootScreen(rootComponent)
            }
        }
    }
}
