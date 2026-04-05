package com.codandotv.streamplayerapp.feature_news.di

import com.codandotv.streamplayerapp.feature_news.presentation.NewsScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

object NewsScreenModule {
    val module = module {
        viewModel {
            NewsScreenViewModel( permissionsManager = get())
        }
    }
}
