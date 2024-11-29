package com.khomichenko.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.khomichenko.settings.component.SettingsComponent

@Composable
fun SettingsScreen(component: SettingsComponent) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "settings")
    }
}
