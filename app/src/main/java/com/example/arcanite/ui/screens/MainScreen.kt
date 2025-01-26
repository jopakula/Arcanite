package com.example.arcanite.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.arcanite.data.network.RequestState
import com.example.arcanite.data.network.openUrl
import com.example.arcanite.ui.viewModels.UsersViewModel
import com.example.arcanite.uikit.cards.UserCard
import com.example.arcanite.uikit.helpfulFunctions.ChangeStatusBarColor
import com.example.arcanite.uikit.inputField.MyInputField
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    navigationController: NavHostController,
    usersViewModel: UsersViewModel = koinViewModel()
) {
    ChangeStatusBarColor(color = MaterialTheme.colorScheme.onBackground, isChangeIconColor = true)

    var query by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val usersState by usersViewModel.usersStateFlow().collectAsState()
    val context = LocalContext.current

    LaunchedEffect(query) {
        if (query.length > 3) {
            usersViewModel.searchUsers(query)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
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
        when (val state = usersState) {
            is RequestState.Idle -> {
                Text(text = "No users.", color = Color.Gray)
            }
            is RequestState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
            is RequestState.Error -> {
                Text(text = "Error: ${state.message}", color = Color.Red)
            }
            is RequestState.Empty -> {
                Text(text = "No users found.", color = Color.Gray)
            }
            is RequestState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.background.copy(alpha = 0.9F))
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    items(state.data) { user ->
                        UserCard(
                            cardText = user.login ?: "Unknown",
                            icon = user.avatarUrl ?: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTNMtcQ0vdyyVRT48spbDkBPl8LYt4g9osEWA&s",
                            onClick = {
                                focusManager.clearFocus()
                                openUrl(
                                    context = context,
                                    url = user.htmlUrl.toString()
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun MainScreenPreview() {
    MainScreen(navigationController = rememberNavController())
}