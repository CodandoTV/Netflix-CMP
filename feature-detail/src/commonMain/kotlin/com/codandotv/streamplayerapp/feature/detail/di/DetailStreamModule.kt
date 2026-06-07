package com.codandotv.streamplayerapp.feature.detail.di

import com.codandotv.streamplayerapp.core.shared.ui.widget.getSharedHandlerPlatform
import com.codandotv.streamplayerapp.feature.detail.data.DetailStreamRepository
import com.codandotv.streamplayerapp.feature.detail.data.DetailStreamRepositoryImpl
import com.codandotv.streamplayerapp.feature.detail.data.DetailStreamService
import com.codandotv.streamplayerapp.feature.detail.data.DetailStreamServiceImpl
import com.codandotv.streamplayerapp.feature.detail.domain.DetailStreamUseCase
import com.codandotv.streamplayerapp.feature.detail.domain.DetailStreamUseCaseImpl
import com.codandotv.streamplayerapp.feature.detail.domain.VideoStreamsUseCase
import com.codandotv.streamplayerapp.feature.detail.domain.VideoStreamsUseCaseImpl
import com.codandotv.streamplayerapp.feature.detail.presentation.screens.DetailStreamViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

object DetailStreamModule {
    val module = module {
        viewModel { (id: String) ->
            DetailStreamViewModel(
                detailStreamUseCase = get {
                    parametersOf(id)
                },
                videoStreamsUseCase = get {
                    parametersOf(id)
                },
                dispatcher = Dispatchers.IO
            )
        }
        factory<DetailStreamUseCase> { (id: String) ->
            DetailStreamUseCaseImpl(
                detailStreamRepository = get {
                    parametersOf(id)
                }
            )
        }
        factory<VideoStreamsUseCase> { (id: String) ->
            VideoStreamsUseCaseImpl(
                detailStreamRepository = get {
                    parametersOf(id)
                }
            )
        }
        factory<DetailStreamRepository> { (id: String) ->
            DetailStreamRepositoryImpl(
                favoriteDao = get(),
                service = get(),
                movieId = id,
            )
        }

        factory<DetailStreamService> {
            DetailStreamServiceImpl(
                client = get()
            )
        }

        factory { getSharedHandlerPlatform() }
    }
}
