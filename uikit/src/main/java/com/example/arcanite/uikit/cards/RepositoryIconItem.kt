package com.example.arcanite.uikit.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uikit.R

@Composable
fun RepositoryIconItem(
    count: Int = 0,
    icon: Painter = painterResource(R.drawable.star)
) {
    Row(
        modifier = Modifier
            .border(width = 0.dp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1F), shape = RoundedCornerShape(8.dp))
            .background(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05F),
                shape = RoundedCornerShape(8.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            modifier = Modifier.padding(PaddingValues(start = 4.dp)),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            text = count.toString(),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5F)
        )
        Icon(
            modifier = Modifier
                .size(24.dp)
                .padding(PaddingValues(end = 4.dp)),
            painter = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5F)
        )
    }
}

@Composable
@Preview
private fun RepositoryIconItemPreview() {
    RepositoryIconItem()
}