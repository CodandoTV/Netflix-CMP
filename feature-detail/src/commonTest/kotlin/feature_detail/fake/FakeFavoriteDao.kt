package feature_detail.fake

import com.codandotv.streamplayerapp.core_local_storage.data.dao.FavoriteDao
import com.codandotv.streamplayerapp.core_local_storage.domain.model.MovieEntity

class FakeFavoriteDao : FavoriteDao {
    private val movies = mutableListOf<MovieEntity>()
    var fetchAllCalled = false
    var insertCalledWith: MovieEntity? = null
    var deleteCalledWith: String? = null

    override suspend fun fetchAll(): List<MovieEntity> {
        fetchAllCalled = true
        return movies.toList()
    }

    override suspend fun insert(favoriteMovie: MovieEntity) {
        insertCalledWith = favoriteMovie
        movies.removeAll { it.id == favoriteMovie.id }
        movies.add(favoriteMovie)
    }

    override suspend fun delete(favoriteMovie: String) {
        deleteCalledWith = favoriteMovie
        movies.removeAll { it.id == favoriteMovie }
    }
}
