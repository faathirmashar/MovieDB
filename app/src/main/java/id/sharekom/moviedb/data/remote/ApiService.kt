package id.sharekom.moviedb.data.remote

import id.sharekom.moviedb.BuildConfig
import id.sharekom.moviedb.data.models.Movie
import id.sharekom.moviedb.data.models.Tv
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/3/movie/popular${BuildConfig.API_KEY}")
    fun getPopularMovie(): Call<Movie>

    @GET("/3/movie/top_rated${BuildConfig.API_KEY}")
    fun getTopRatedMovie(): Call<Movie>

    @GET("/3/movie/upcoming${BuildConfig.API_KEY}")
    fun getUpcomingMovie(): Call<Movie>

    @GET("/3/movie/now_playing${BuildConfig.API_KEY}")
    fun getNowPlayingMovie(): Call<Movie>

    @GET("/3/search/movie${BuildConfig.API_KEY}")
    fun getSearchMovie(@Query("query") searchQuery: String): Call<Movie>

    @GET("/3/tv/popular${BuildConfig.API_KEY}")
    fun getPopularTv(): Call<Tv>

    @GET("/3/tv/top_rated${BuildConfig.API_KEY}")
    fun getTopRatedTv(): Call<Tv>

    @GET("/3/tv/on_the_air${BuildConfig.API_KEY}")
    fun getOnTheAirTv(): Call<Tv>

    @GET("/3/tv/airing_today${BuildConfig.API_KEY}")
    fun getAiringTodayTv(): Call<Tv>

    @GET("/3/search/tv${BuildConfig.API_KEY}")
    fun getSearchTv(@Query("query") searchQuery: String): Call<Tv>
}