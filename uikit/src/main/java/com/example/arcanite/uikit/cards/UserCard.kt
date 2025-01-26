package com.example.arcanite.uikit.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun UserCard(
    cardText: String = "",
    icon: String = "",
    colorBG: Color = MaterialTheme.colorScheme.background,
    shadowElevation: Dp = 8.dp,
    roundingSize: Dp = 8.dp,
    showShadow: Boolean = true,
    onClick: () -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }
    val shadowColor = if (isSystemInDarkTheme()) {
        Color(0xFFFFFFFF)
    } else {
        Color(0xFF000000)
    }
    val shadowModifier = when(showShadow){
        true -> Modifier.shadow(
            elevation = shadowElevation,
            shape = RoundedCornerShape(roundingSize),
            ambientColor = shadowColor,
            spotColor = shadowColor,
        )
        else -> Modifier
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(shadowModifier)
            .clip(RoundedCornerShape(roundingSize))
            .background(color = colorBG, shape = RoundedCornerShape(roundingSize))
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = ripple(),
            ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    model = icon,
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = cardText,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }

            Icon(
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5F)
            )
        }
    }
}

@Composable
@Preview
private fun UserCardPreview() {
    Column {
        repeat(5) {
            UserCard()
        }
    }
}