package id.sharekom.moviedb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.sharekom.moviedb.data.MoviesTvRepository
import id.sharekom.moviedb.data.local.MovieTvEntity
import id.sharekom.moviedb.data.models.MovieResult
import id.sharekom.moviedb.data.models.TvResult

class MovieTvViewModel(private val moviesTvRepository: MoviesTvRepository) : ViewModel() {
    // Movie
    fun getTopRatedMovies(): LiveData<ArrayList<MovieResult>> = moviesTvRepository.getTopRatedMovie()
    fun getPopularMovies(): LiveData<ArrayList<MovieResult>> = moviesTvRepository.getPopularMovies()
    fun getUpcomingMovies(): LiveData<ArrayList<MovieResult>> = moviesTvRepository.getUpcomingMovie()
    fun getNowPlayingMovies(): LiveData<ArrayList<MovieResult>> = moviesTvRepository.getNowPlayingMovie()
    fun getSearchMovie(searchQuery: String): LiveData<ArrayList<MovieResult>> = moviesTvRepository.getSearchMovie(searchQuery)

    // Tv
    fun getPopularTv(): LiveData<ArrayList<TvResult>> = moviesTvRepository.getPopularTv()
    fun getTopRatedTv(): LiveData<ArrayList<TvResult>> = moviesTvRepository.getTopRatedTv()
    fun getOnTheAirTv(): LiveData<ArrayList<TvResult>> = moviesTvRepository.getOnTheAirTv()
    fun getAiringToday(): LiveData<ArrayList<TvResult>> = moviesTvRepository.getAiringTodayTv()
    fun getSearchTv(searchQuery: String): LiveData<ArrayList<TvResult>> = moviesTvRepository.getSearchTv(searchQuery)

    // Favorite
    fun addToFavorite(movieTvEntity: MovieTvEntity) {
        moviesTvRepository.addToFavorite(movieTvEntity)
    }
    fun getFavoriteById(id: Int): LiveData<List<MovieTvEntity>> = moviesTvRepository.getFavoriteById(id)
    fun getAllFavorite(): LiveData<List<MovieTvEntity>> = moviesTvRepository.getAllFavorite()
    fun deleteFavorite(moviteTvEntity: MovieTvEntity) {
        moviesTvRepository.deleteFavorite(moviteTvEntity)
    }
}