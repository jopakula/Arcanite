package com.example.arcanite.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
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
import androidx.compose.runtime.snapshotFlow
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
import com.example.arcanite.navigation.Screens
import com.example.arcanite.ui.viewModels.CombinedSearchViewModel
import com.example.arcanite.uikit.helpfulFunctions.ChangeStatusBarColor
import com.example.arcanite.uikit.inputField.MyInputField
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import org.koin.androidx.compose.koinViewModel


@OptIn(FlowPreview::class)
@Composable
fun MainScreen(
    navigationController: NavHostController,
    combinedViewModel: CombinedSearchViewModel = koinViewModel()
) {
    ChangeStatusBarColor(color = MaterialTheme.colorScheme.onBackground, isChangeIconColor = true)

    var query by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val combinedState by combinedViewModel.combinedStateFlow().collectAsState()
    val context = LocalContext.current

    LaunchedEffect(query) {
        snapshotFlow { query }
            .debounce(500)
            .collectLatest {
                if (it.length > 3) {
                    combinedViewModel.search(it)
                }
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
        when (combinedState) {
            is RequestState.Idle -> Text("Введите запрос и нажмите поиск")
            is RequestState.Loading -> CircularProgressIndicator()
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
            is RequestState.Empty -> Text("Ничего не найдено")
            is RequestState.Error -> Text(
                "Ошибка: ${(combinedState as RequestState.Error).message}",
                color = Color.Red
            )
        }

    }
}

@Composable
@Preview
private fun MainScreenPreview() {
    MainScreen(navigationController = rememberNavController())
}