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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.arcanite.data.network.RequestState
import com.example.arcanite.navigation.Screens
import com.example.arcanite.ui.viewModels.CombinedSearchViewModel
import com.example.arcanite.uikit.helpfulFunctions.ChangeStatusBarColor
import com.example.arcanite.uikit.inputField.MyInputField
import com.example.arcanite.uikit.lists.ShimmerListItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    navigationController: NavHostController,
    combinedViewModel: CombinedSearchViewModel = koinViewModel()
) {
    ChangeStatusBarColor(color = MaterialTheme.colorScheme.onBackground, isChangeIconColor = true)

    val query by combinedViewModel.getQuery()
    val focusManager = LocalFocusManager.current
    val combinedState by combinedViewModel.combinedStateFlow().collectAsState()
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
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
                .padding(start = 12.dp, end = 12.dp, bottom = 8.dp, top = 16.dp),
        ) {
            MyInputField(
                text = query,
                onValueChange = { combinedViewModel.setQuery(it) },
            )
        }
        when (combinedState) {
            is RequestState.Idle -> {
                IdleScreen()
            }
            is RequestState.Loading -> {
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    repeat(10) {
                        ShimmerListItem(isLoading = true) {
                        }
                    }
                }
            }
            is RequestState.Success -> CombinedList(
                items = (combinedState as RequestState.Success<List<SearchResultItem>>).data,
                context = context,
                onRepositoryCardClick = { repo ->
                    navigationController.navigate(
                        Screens.File.createRoute(
                            owner = repo.owner?.login ?: "",
                            repo = repo.name ?: "",
                            path = ""
                        )
                    )
                }
            )

            is RequestState.Empty -> {
                EmptyScreen()
            }
            is RequestState.Error -> {
                ErrorScreen(
                    onClick = { combinedViewModel.search(query = query)}
                )
            }
        }
    }
}

@Composable
@Preview
private fun MainScreenPreview() {
    MainScreen(navigationController = rememberNavController())
}