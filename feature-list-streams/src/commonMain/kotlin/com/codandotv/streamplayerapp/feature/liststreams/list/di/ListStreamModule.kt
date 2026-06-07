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
            ListStreamViewModel(
                listStreams = get(),
                listGenres = get(),
                latestStream = get()
            )
        }

        factory<ListStreamUseCase> {
            ListStreamUseCaseImpl(
                repository = get()
            )
        }

        factory<GetGenresUseCase> {
            GetGenresUseCaseImpl(
                repository = get()
            )
        }

        factory<GetTopRatedStream> {
            GetTopRatedStreamImpl(
                repository = get()
            )
        }

        factory<ListStreamAnalytics> {
            ListStreamAnalyticsImpl()
        }

        factory<ListStreamRepository> {
            ListStreamRepositoryImpl(
                service = get(),
            )
        }

        factory<ListStreamService> {
            ListStreamServiceImpl(
                client = get()
            )
        }
    }
}
