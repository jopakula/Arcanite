package com.example.arcanite.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.arcanite.R

@Composable
fun IdleScreen() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.folder))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        LottieAnimation(
            modifier = Modifier
                .padding(top = 40.dp)
                .size(200.dp),
            composition = composition,
            iterations = LottieConstants.IterateForever,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Поиск",
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 26.sp
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Начните вводить поисковый запрос (от 3-х символов), чтобы увидеть его результаты \uD83E\uDD13",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8F),
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
    }
}