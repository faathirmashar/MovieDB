package id.sharekom.moviedb.data

import androidx.lifecycle.LiveData
import id.sharekom.moviedb.data.local.LocalDataSource
import id.sharekom.moviedb.data.local.MovieTvEntity
import id.sharekom.moviedb.data.models.MovieResult
import id.sharekom.moviedb.data.models.TvResult
import id.sharekom.moviedb.data.remote.RemoteDataSource

class MoviesTvRepository private constructor(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource): MovieTvDataSource {
    companion object {
        @Volatile
        private var instance: MoviesTvRepository? = null

        fun getInstance(remoteData: RemoteDataSource, localDataSource: LocalDataSource): MoviesTvRepository =
            instance ?: synchronized(this) {
                MoviesTvRepository(remoteData, localDataSource).apply { instance = this }
            }
    }

    // Movies
    override fun getTopRatedMovie(): LiveData<ArrayList<MovieResult>> = remoteDataSource.getTopRatedMovies()
    override fun getUpcomingMovie(): LiveData<ArrayList<MovieResult>> = remoteDataSource.getUpcomingMovies()
    override fun getNowPlayingMovie(): LiveData<ArrayList<MovieResult>> = remoteDataSource.getNowPlayingMovies()
    override fun getSearchMovie(searchQuery: String): LiveData<ArrayList<MovieResult>> = remoteDataSource.getSearchMovie(searchQuery)
    override fun getPopularMovies(): LiveData<ArrayList<MovieResult>> = remoteDataSource.getPopularMovies()

    // TvShows
    override fun getPopularTv(): LiveData<ArrayList<TvResult>> = remoteDataSource.getPopularTv()
    override fun getTopRatedTv(): LiveData<ArrayList<TvResult>> = remoteDataSource.getTopRatedTv()
    override fun getOnTheAirTv(): LiveData<ArrayList<TvResult>> = remoteDataSource.getOnTheAirTv()
    override fun getAiringTodayTv(): LiveData<ArrayList<TvResult>> = remoteDataSource.getAiringTodayTv()
    override fun getSearchTv(searchQuery: String): LiveData<ArrayList<TvResult>> = remoteDataSource.getSearchTv(searchQuery)

    // Favorites
    override fun addToFavorite(movieTvEntity: MovieTvEntity) {
        localDataSource.insertMovie(movieTvEntity)
    }
    override fun getAllFavorite(): LiveData<List<MovieTvEntity>> = localDataSource.getMovies()
    override fun deleteFavorite(movieTvEntity: MovieTvEntity) {
        localDataSource.deleteMovie(movieTvEntity)
    }
    override fun getFavoriteById(id: Int): LiveData<List<MovieTvEntity>> = localDataSource.getFavoriteById(id)
}