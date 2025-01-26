package com.example.arcanite.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.arcanite.uikit.cards.RepositoryCard
import com.example.arcanite.uikit.cards.UserCard
import com.example.arcanite.uikit.helpfulFunctions.ChangeStatusBarColor
import com.example.arcanite.uikit.inputField.MyInputField

@Composable
fun MainScreen(
    navigationController: NavHostController,
) {
    ChangeStatusBarColor(color = MaterialTheme.colorScheme.onBackground, isChangeIconColor = true)
    var query by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .systemBarsPadding()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.onBackground)
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, bottom = 8.dp, top = 16.dp)

        ) {
            MyInputField(
                text = query,
                onValueChange = { query = it },
            )
        }
        LazyColumn(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(5){
                UserCard(
                    cardText = "Cat",
                    icon = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTNMtcQ0vdyyVRT48spbDkBPl8LYt4g9osEWA&s",
                    onClick = { focusManager.clearFocus()}
                )
            }
            items(15){
                RepositoryCard(
                    cardNameText = "Cat",
                    stars = 12,
                    watchers = 94,
                    forks = 7,
                    createdText = "08.11.2015 09:41:44",
                    updatedText = "23.06.2024 05:19:23",
                    descriptionText = ":baby: Superbook: ABC's for babies!",
                    onMoreTextClick = { focusManager.clearFocus()}
                )
            }
        }
    }
}

@Composable
@Preview
private fun MainScreenPreview(){
    MainScreen(navigationController = rememberNavController())
}