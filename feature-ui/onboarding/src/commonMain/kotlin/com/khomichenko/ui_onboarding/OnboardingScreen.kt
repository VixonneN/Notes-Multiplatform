@file:OptIn(ExperimentalAdaptiveApi::class)

package com.khomichenko.ui_onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.khomichenko.onboarding.component.OnboardingComponent
import com.khomichenko.ui_onboarding.composables.OnboardingPage
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveScaffold
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun OnboardingScreen(component: OnboardingComponent) {
    AdaptiveScaffold { paddingValues ->
        OnboardingMain(
            component = component,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun OnboardingMain(
    component: OnboardingComponent,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val pagerState = rememberPagerState(0) { 3 }
        HorizontalPager(state = pagerState) { currentPage ->
            //Box в который передается currentPage
            OnboardingPage(
                currentPage = currentPage,
                component = component
            )
        }
    }

}

@Preview
@Composable
private fun NewButton() {
    Button(
        onClick = {}
    ) {
        Text("PIDOR")
    }
}