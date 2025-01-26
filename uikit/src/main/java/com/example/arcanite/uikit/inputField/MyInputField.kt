package com.example.arcanite.uikit.inputField

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.arcanite.uikit.common.YellowColor

@Composable
fun MyInputField(
    text: String? = null,
    onValueChange: (String) -> Unit = {},
    hint: String = "Поиск",
    textOptional: String = "Отменить"
) {
    val focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }
    val focusManager = LocalFocusManager.current
    var isFocused by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .height(50.dp,)
                .weight(1F)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                ) { focusRequester.requestFocus() }
                .background(color = MaterialTheme.colorScheme.background, shape = RoundedCornerShape(8.dp))
                .border(
                    width = 0.dp,
                    color = YellowColor,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(PaddingValues(vertical = 6.dp, horizontal = 8.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.45F)
            )
            Box(modifier = Modifier.weight(1F)) {
                if (text.isNullOrEmpty() && hint.isNotEmpty()) {
                    Text(
                        modifier = Modifier.padding(PaddingValues(start = 8.dp, end = 8.dp)),
                        fontSize = 18.sp,
                        text = hint,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5F)
                    )
                }
                BasicTextField(
                    modifier = Modifier
                        .padding(PaddingValues(start = 8.dp, end = 8.dp))
                        .focusRequester(focusRequester)
                        .onFocusChanged { isFocused = it.isFocused },
                    value = text ?: "",
                    onValueChange = onValueChange,
                    maxLines = 1,
                    singleLine = true,
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    cursorBrush = SolidColor(YellowColor)
                )
            }
            if (!text.isNullOrEmpty()){
                Icon(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .size(28.dp)
                        .clip(CircleShape)
                        .clickable { onValueChange("") },
                    imageVector = Icons.Rounded.Clear,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.45F)
                )
            }
        }
        AnimatedVisibility(visible = isFocused) {
            Row(
                modifier = Modifier
                    .padding(6.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        onValueChange("")
                        focusManager.clearFocus()
                    },
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 6.dp, vertical = 4.dp),
                    text = textOptional,
                    color = MaterialTheme.colorScheme.background,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
@Preview
private fun MyInputFieldPreview() {
    MyInputField(
        hint = "Hint",
    )
}

