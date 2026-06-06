package com.codandotv.streamplayerapp.feature.liststreams.list.di

import com.codandotv.streamplayerapp.feature.liststreams.list.data.ListStreamRepository
import com.codandotv.streamplayerapp.feature.liststreams.list.data.ListStreamRepositoryImpl
import com.codandotv.streamplayerapp.feature.liststreams.list.data.ListStreamService
import com.codandotv.streamplayerapp.feature.liststreams.list.data.ListStreamServiceImpl
import com.codandotv.streamplayerapp.feature.liststreams.list.domain.GetGenresUseCase
import com.codandotv.streamplayerapp.feature.liststreams.list.domain.GetGenresUseCaseImpl
import com.codandotv.streamplayerapp.feature.liststreams.list.domain.GetTopRatedStream
import com.codandotv.streamplayerapp.feature.liststreams.list.domain.GetTopRatedStreamImpl
import com.codandotv.streamplayerapp.feature.liststreams.list.domain.ListStreamAnalytics
import com.codandotv.streamplayerapp.feature.liststreams.list.domain.ListStreamAnalyticsImpl
import com.codandotv.streamplayerapp.feature.liststreams.list.domain.ListStreamUseCase
import com.codandotv.streamplayerapp.feature.liststreams.list.domain.ListStreamUseCaseImpl
import com.codandotv.streamplayerapp.feature.liststreams.list.presentation.screens.ListStreamViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

object ListStreamModule {
    val module = module {
        viewModel {
            _root_ide_package_.com.codandotv.streamplayerapp.feature.liststreams.list.presentation.screens.ListStreamViewModel(
                listStreams = get(),
                listGenres = get(),
                latestStream = get()
            )
        }

        factory<com.codandotv.streamplayerapp.feature.liststreams.list.domain.ListStreamUseCase> {
            _root_ide_package_.com.codandotv.streamplayerapp.feature.liststreams.list.domain.ListStreamUseCaseImpl(
                repository = get()
            )
        }

        factory<com.codandotv.streamplayerapp.feature.liststreams.list.domain.GetGenresUseCase> {
            _root_ide_package_.com.codandotv.streamplayerapp.feature.liststreams.list.domain.GetGenresUseCaseImpl(
                repository = get()
            )
        }

        factory<com.codandotv.streamplayerapp.feature.liststreams.list.domain.GetTopRatedStream> {
            _root_ide_package_.com.codandotv.streamplayerapp.feature.liststreams.list.domain.GetTopRatedStreamImpl(
                repository = get()
            )
        }

        factory<com.codandotv.streamplayerapp.feature.liststreams.list.domain.ListStreamAnalytics> {
            _root_ide_package_.com.codandotv.streamplayerapp.feature.liststreams.list.domain.ListStreamAnalyticsImpl()
        }

        factory<com.codandotv.streamplayerapp.feature.liststreams.list.data.ListStreamRepository> {
            _root_ide_package_.com.codandotv.streamplayerapp.feature.liststreams.list.data.ListStreamRepositoryImpl(
                service = get(),
            )
        }

        factory<com.codandotv.streamplayerapp.feature.liststreams.list.data.ListStreamService> {
            _root_ide_package_.com.codandotv.streamplayerapp.feature.liststreams.list.data.ListStreamServiceImpl(
                client = get()
            )
        }
    }
}
