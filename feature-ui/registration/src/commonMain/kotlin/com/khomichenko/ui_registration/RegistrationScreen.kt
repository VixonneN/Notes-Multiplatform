package com.khomichenko.ui_registration

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.khomichenko.registration.component.RegistrationComponent

@Composable
fun RegistrationScreen(component: RegistrationComponent) {
    Scaffold {
        Text(text = "Registration", modifier = Modifier.padding(it))
    }
}