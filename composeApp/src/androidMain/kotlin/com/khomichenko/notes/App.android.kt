package com.khomichenko.notes

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import com.arkivanov.decompose.defaultComponentContext
import com.khomichenko.notes.di.AppModule
import com.khomichenko.root.component.RootComponent
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


        setContent {
            CompositionLocalProvider(
                MainLifecycleOwner provides rootComponent
            ) {
                RootScreen(rootComponent)
            }
        }
    }
}
