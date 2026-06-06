package com.codandotv.streamplayerapp.feature.liststreams.list.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.codandotv.streamplayerapp.core.networking.handleError.NetworkResponse
import com.codandotv.streamplayerapp.feature.liststreams.list.domain.toListStream

@Suppress("TooGenericExceptionCaught", "UseCheckOrError")
class StreamDataSource(
    private val service: com.codandotv.streamplayerapp.feature.liststreams.list.data.ListStreamService,
    private val genreId: Long,
    private val genreName: String,
) : PagingSource<Int, com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.Stream>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.Stream> {
        val nextPageNumber = params.key ?: _root_ide_package_.com.codandotv.streamplayerapp.feature.liststreams.list.data.StreamDataSource.Companion.START_PAGE_INDEX

        return try {
            val response = service.getPaginatedMovies(
                genres = genreId.toString(),
                page = nextPageNumber
            )

            if (response is NetworkResponse.Success) {
                LoadResult.Page(
                    data = response.value.toListStream(genreName).streams,
                    prevKey = if (nextPageNumber > 1) nextPageNumber - 1 else null,
                    nextKey = nextPageNumber.plus(1)
                )
            } else {
                throw IllegalStateException("Something wrong")
            }
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.Stream>): Int? = null

    companion object {
        private const val START_PAGE_INDEX = 1
    }
}
