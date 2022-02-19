package id.sharekom.moviedb.data

import androidx.lifecycle.LiveData
import id.sharekom.moviedb.data.local.MovieTvEntity
import id.sharekom.moviedb.data.models.MovieResult
import id.sharekom.moviedb.data.models.TvResult

interface MovieTvDataSource {
    // Movie
    fun getPopularMovies(): LiveData<ArrayList<MovieResult>>
    fun getTopRatedMovie() : LiveData<ArrayList<MovieResult>>
    fun getUpcomingMovie() : LiveData<ArrayList<MovieResult>>
    fun getNowPlayingMovie() : LiveData<ArrayList<MovieResult>>
    fun getSearchMovie(searchQuery: String) : LiveData<ArrayList<MovieResult>>

    // Tv
    fun getPopularTv(): LiveData<ArrayList<TvResult>>
    fun getTopRatedTv(): LiveData<ArrayList<TvResult>>
    fun getOnTheAirTv(): LiveData<ArrayList<TvResult>>
    fun getAiringTodayTv(): LiveData<ArrayList<TvResult>>
    fun getSearchTv(searchQuery: String): LiveData<ArrayList<TvResult>>

    // Favorite
    fun addToFavorite(movieTvEntity: MovieTvEntity)
    fun getAllFavorite(): LiveData<List<MovieTvEntity>>
    fun deleteFavorite(movieTvEntity: MovieTvEntity)
    fun getFavoriteById(id: Int): LiveData<List<MovieTvEntity>>
}