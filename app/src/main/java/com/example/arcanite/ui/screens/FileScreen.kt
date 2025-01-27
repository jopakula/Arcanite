package com.example.arcanite.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.arcanite.data.network.RequestState
import com.example.arcanite.data.network.openUrl
import com.example.arcanite.navigation.Screens
import com.example.arcanite.ui.viewModels.FileViewModel
import com.example.arcanite.uikit.cards.RepoFileItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun FileScreen(
    navigationController: NavHostController,
    owner: String,
    repo: String,
    path: String,
    fileViewModel: FileViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        fileViewModel.loadFiles(owner, repo, path)
    }
    val context = LocalContext.current
    val filesState by fileViewModel.filesState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.onBackground)
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Button(onClick = { navigationController.popBackStack() }) {
                Text("Назад")
            }
        }

        when (val state = filesState) {
            is RequestState.Loading -> CircularProgressIndicator()
            is RequestState.Success -> {
                LazyColumn {
                    items(state.data) { file ->
                        RepoFileItem(
                            name = file.name ?: "Без имени",
                            type = file.type ?: "unknown",
                            size = file.size,
                            onClick = {
                                if (file.type == "dir") {
                                    navigationController.navigate(
                                        Screens.File.createRoute(
                                            owner = owner,
                                            repo = repo,
                                            path = file.path ?: ""
                                        )
                                    )
                                } else {
                                    file.downloadUrl?.let { url ->
                                        openUrl(context = context, url = url)
                                    }
                                }
                            }
                        )
                    }
                }
            }
            is RequestState.Empty -> Text("Папка пуста")
            is RequestState.Error -> Text(
                "Ошибка: ${state.message}",
                color = Color.Red
            )
            else -> Text("Нет данных")
        }

    }
}


@Composable
@Preview
private fun FileScreenPreview() {
}