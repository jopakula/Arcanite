package com.example.arcanite.uikit.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.arcanite.uikit.common.FileColor
import com.example.arcanite.uikit.common.FolderColor
import com.example.arcanite.uikit.helpfulFunctions.formatFileSize
import com.example.uikit.R

@Composable
fun RepoFileItem(
    name: String = "",
    type: String = "",
    size: Int? = null,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val icon = if (type == "dir") {
            R.drawable.folder
        } else {
            R.drawable.file
        }
        val color = if (type == "dir") {
            FolderColor
        } else {
            FileColor
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable(onClick = onClick),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    painter = painterResource(icon),
                    contentDescription = null,
                    tint = color,
                )
                Text(
                    text = name,
                    fontWeight = FontWeight.Medium
                    )
            }
            if (size != 0) {
                Text(
                    text = formatFileSize(size!!),
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7F)
                )
            }
        }
    }
}
