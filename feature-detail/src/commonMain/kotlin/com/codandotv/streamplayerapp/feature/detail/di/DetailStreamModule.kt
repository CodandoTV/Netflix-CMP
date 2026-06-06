package com.codandotv.streamplayerapp.feature.detail.di

import com.codandotv.streamplayerapp.core.shared.ui.widget.getSharedHandlerPlatform
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

object DetailStreamModule {
    val module = module {
        viewModel { (id: String) ->
            _root_ide_package_.com.codandotv.streamplayerapp.feature.detail.presentation.screens.DetailStreamViewModel(
                detailStreamUseCase = get {
                    parametersOf(id)
                },
                videoStreamsUseCase = get {
                    parametersOf(id)
                },
                dispatcher = Dispatchers.IO
            )
        }
        factory<com.codandotv.streamplayerapp.feature.detail.domain.DetailStreamUseCase> { (id: String) ->
            _root_ide_package_.com.codandotv.streamplayerapp.feature.detail.domain.DetailStreamUseCaseImpl(
                detailStreamRepository = get {
                    parametersOf(id)
                }
            )
        }
        factory<com.codandotv.streamplayerapp.feature.detail.domain.VideoStreamsUseCase> { (id: String) ->
            _root_ide_package_.com.codandotv.streamplayerapp.feature.detail.domain.VideoStreamsUseCaseImpl(
                detailStreamRepository = get {
                    parametersOf(id)
                }
            )
        }
        factory<com.codandotv.streamplayerapp.feature.detail.data.DetailStreamRepository> { (id: String) ->
            _root_ide_package_.com.codandotv.streamplayerapp.feature.detail.data.DetailStreamRepositoryImpl(
                favoriteDao = get(),
                service = get(),
                movieId = id,
            )
        }

        factory<com.codandotv.streamplayerapp.feature.detail.data.DetailStreamService> {
            _root_ide_package_.com.codandotv.streamplayerapp.feature.detail.data.DetailStreamServiceImpl(
                client = get()
            )
        }

        factory { _root_ide_package_.com.codandotv.streamplayerapp.core.shared.ui.widget.getSharedHandlerPlatform() }
    }
}
