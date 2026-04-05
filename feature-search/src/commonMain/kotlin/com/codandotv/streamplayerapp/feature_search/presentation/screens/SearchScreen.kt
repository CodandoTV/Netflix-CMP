package com.codandotv.streamplayerapp.feature_search.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.codandotv.streamplayerapp.core_navigation.extensions.goBack
import com.codandotv.streamplayerapp.feature_search.domain.mapper.toSearchStreamCardModel
import com.codandotv.streamplayerapp.feature_search.presentation.widgets.SearchStreamCard
import com.codandotv.streamplayerapp.feature_search.presentation.widgets.SearchableTopBar
import com.codandotv.streamplayerapp.feature_search.presentation.widgets.StreamsEmpty
import com.codandotv.streamplayerapp.feature_search.presentation.widgets.StreamsError
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import streamplayerapp_kmp.feature_search.generated.resources.Res
import streamplayerapp_kmp.feature_search.generated.resources.search_list_describle

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel(),
    onNavigateDetailList: (String) -> Unit = {},
    navController: NavController,
) {

    val uiState by viewModel.uiState.collectAsState()
    when (uiState) {
        is SearchUIState.Success -> {
            SetupSearchScreen(
                navController = navController,
                uiState = uiState as SearchUIState.Success,
                viewModel = viewModel,
                onNavigateDetailList = onNavigateDetailList
            )
        }

        is SearchUIState.Error -> {
            StreamsError(
                onRetry = { viewModel.onTryAgain() },
                onCloseButton = { navController.goBack() }
            )
        }

        is SearchUIState.Empty -> {
            SetupSearchScreen(
                navController = navController,
                uiState = uiState as SearchUIState.Empty,
                viewModel = viewModel,
                onNavigateDetailList = onNavigateDetailList
            )
        }

        else -> {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }
        }
    }
}

@Composable
private fun SetupSearchScreen(
    onNavigateDetailList: (String) -> Unit = {},
    navController: NavController,
    uiState: SearchUIState,
    viewModel: SearchViewModel
) {
    Scaffold(
        topBar = {
            val currentText by viewModel.currentSearchText.collectAsState()
            SearchableTopBar(
                currentSearchText = currentText,
                onSearchTextChanged = { value ->
                    viewModel.setCurrentSearchText(
                        newText = value
                    )
                },
                onSearchDispatched = {
                    viewModel.fetchMovies()
                },
                onSearchIconPressed = {
                    viewModel.fetchMovies()
                },
                onBackPressed = {
                    navController.goBack()
                },
                onCleanTextPressed = {
                    viewModel.onCleanText()
                }
            )
        }
    ) { paddingValues ->

        if (uiState is SearchUIState.Success) {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState()),
            ) {
                Text(
                    text = stringResource(Res.string.search_list_describle),
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                uiState.listCharacters.results.map {
                    SearchStreamCard(
                        content = it.toSearchStreamCardModel(),
                        onSearchStreamPressed = { id ->
                            onNavigateDetailList(id)
                        }
                    )
                }
            }
        } else {
            Box(modifier = Modifier.padding(paddingValues)) {
                StreamsEmpty()
            }
        }
    }
}
