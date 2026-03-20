package com.codandotv.streamplayerapp.feature_list_streams.list.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codandotv.streamplayerapp.core_navigation.bottomnavigation.StreamPlayerBottomNavigation
import com.codandotv.streamplayerapp.core_shared_ui.widget.StreamPlayerTopBar
import com.codandotv.streamplayerapp.feature_list_streams.list.presentation.widgets.HighlightBanner
import com.codandotv.streamplayerapp.core_shared_ui.widget.StreamsCarousel
import org.koin.compose.viewmodel.koinViewModel

@Suppress("LongParameterList")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListStreamsScreen(
    viewModel: ListStreamViewModel = koinViewModel(),
    navController: NavController,
    onNavigateDetailList: (String) -> Unit = {},
    onNavigateProfilePicker: () -> Unit = {},
    onNavigateSearchScreen: () -> Unit = {},
    profilePicture: String
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val baseScrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            StreamPlayerTopBar(
                scrollBehavior = scrollBehavior,
                onNavigateProfilePicker = onNavigateProfilePicker,
                onNavigateSearchScreen = onNavigateSearchScreen,
                onSelectedProfilePicture = profilePicture
            )
        },
        bottomBar = {
            StreamPlayerBottomNavigation(navController = navController)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.TopCenter)
                        .verticalScroll(baseScrollState)
                ) {

                    HighlightBanner(data = uiState.highlightBanner)

                    uiState.streamsCarouselContent.forEach { streamCarouselContent ->
                        StreamsCarousel(
                            content = streamCarouselContent,
                            onNavigateDetailList = onNavigateDetailList,
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}

