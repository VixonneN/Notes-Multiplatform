package com.khomichenko.ui_onboarding.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.khomichenko.onboarding.component.OnboardingComponent
import org.jetbrains.compose.ui.tooling.preview.Preview
import com.khomichenko.ui.resources.Res
import com.khomichenko.ui.resources.notes_first
import com.khomichenko.ui.resources.notes_second
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun OnboardingPage(currentPage: Int, component: OnboardingComponent) {
    when (currentPage) {
        0 -> FirstPage()
        1 -> SecondPage()
        2 -> LastPage(component)
    }
}

@Composable
private fun LastPage(component: OnboardingComponent) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = component::navigateToAuth
        ) {
            Text(text = "To Notes")
        }
    }
}

@Preview
@Composable
private fun FirstPage() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(Res.drawable.notes_first),
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun SecondPage() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(Res.drawable.notes_second),
            contentDescription = null
        )
    }
}