package com.example.arcanite.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    val folderName = remember(path) {
        path.substringAfterLast("/").ifEmpty { repo }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .height(74.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.onBackground)
                .padding(start = 16.dp, top = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navigationController.popBackStack() },
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.background,
            )

            Text(
                text = folderName,
                fontSize = 18.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.background
            )
        }

        when (val state = filesState) {
            is RequestState.Loading -> {}
            is RequestState.Success -> {
                val folders = state.data.filter { it.type == "dir" }.sortedBy { it.name }
                val files = state.data.filter { it.type != "dir" }.sortedBy { it.name }

                val sortedFiles = folders + files
                LazyColumn {
                    items(sortedFiles) { file ->
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
                        HorizontalDivider(
                            modifier = Modifier
                                .padding(horizontal = 8.dp),
                            thickness = 0.dp,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2F)
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