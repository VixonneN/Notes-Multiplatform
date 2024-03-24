package com.khomichenko.ui_onboarding.composables

import `Multiplatform-App`.`feature-ui`.onboarding.MR
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.khomichenko.onboarding.component.OnboardingComponent
import dev.icerock.moko.resources.compose.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun OnboardingPage(currentPage: Int, component: OnboardingComponent) {
    when (currentPage) {
        0 -> FirstPage()

        1 -> SecondPage()

        2 -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                TextButton(
                    onClick = component::navigateToAuth,
                ) {
                    Text(text = "To LOGIN")
                }
            }
        }
    }
}

@Preview
@Composable
private fun FirstPage() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(MR.images.notes_first),
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
            painter = painterResource(MR.images.notes_secong),
            contentDescription = null
        )
    }
}