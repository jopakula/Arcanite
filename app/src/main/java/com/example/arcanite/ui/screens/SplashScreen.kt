package com.example.arcanite.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.arcanite.R
import com.example.arcanite.navigation.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigationController: NavController,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash))
    val alpha = remember { Animatable(0F) }

    LaunchedEffect(key1 = true) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(500)
        )
        delay(3500)
        navigationController.popBackStack()
        navigationController.navigate(Screens.Main.screen) {
            popUpTo(0) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center

    ) {

        LottieAnimation(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.Center),
            composition = composition,
            iterations = LottieConstants.IterateForever,
            contentScale = ContentScale.Crop
        )
    }
}


@Composable
@Preview
private fun SplashScreenPreview() {
    SplashScreen(navigationController = rememberNavController())
}