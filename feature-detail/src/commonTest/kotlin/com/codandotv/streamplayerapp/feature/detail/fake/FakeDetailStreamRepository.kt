import com.codandotv.streamplayerapp.feature.detail.data.DetailStreamRepository
import com.codandotv.streamplayerapp.feature.detail.domain.DetailStream
import com.codandotv.streamplayerapp.feature.detail.domain.VideoStream
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeDetailStreamRepository(
    private val movie: DetailStream
) : DetailStreamRepository {

    var getMovieCalled = false
    var deleteCalledWith: String? = null
    var insertCalledWith: DetailStream? = null
    var isFavoriteCalledWith: String? = null

    override suspend fun getMovie(): Flow<DetailStream> {
        getMovieCalled = true
        return flowOf(movie)
    }

    override suspend fun deleteFromMyList(movie: String) {
        deleteCalledWith = movie
    }

    override suspend fun insertToMyList(movie: DetailStream) {
        insertCalledWith = movie
    }

    override suspend fun isFavorite(movieId: String): Boolean {
        isFavoriteCalledWith = movieId
        return false
    }

    override suspend fun getVideoStreams(): Flow<List<VideoStream>> {
        return flowOf(emptyList())
    }
}
