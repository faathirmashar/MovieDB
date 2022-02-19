package id.sharekom.moviedb.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.sharekom.moviedb.data.models.Movie
import id.sharekom.moviedb.data.models.MovieResult
import id.sharekom.moviedb.data.models.Tv
import id.sharekom.moviedb.data.models.TvResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiService: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource(apiService).apply { instance = this }
            }
    }

    fun getPopularMovies(): LiveData<ArrayList<MovieResult>> {
        val resultData = MutableLiveData<ArrayList<MovieResult>>()

        apiService.getPopularMovie().enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        resultData.postValue(body.results)
                    }
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                t.printStackTrace()
            }

        })

        return resultData
    }

    fun getTopRatedMovies(): LiveData<ArrayList<MovieResult>> {
        val resultData = MutableLiveData<ArrayList<MovieResult>>()

        apiService.getTopRatedMovie().enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        resultData.postValue(body.results)
                    }
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return resultData
    }

    fun getUpcomingMovies(): LiveData<ArrayList<MovieResult>> {
        val resultData = MutableLiveData<ArrayList<MovieResult>>()

        apiService.getUpcomingMovie().enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        resultData.postValue(body.results)
                    }
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return resultData
    }

    fun getNowPlayingMovies(): LiveData<ArrayList<MovieResult>> {
        val resultData = MutableLiveData<ArrayList<MovieResult>>()

        apiService.getNowPlayingMovie().enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        resultData.postValue(body.results)
                    }
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return resultData
    }

    fun getSearchMovie(searchQuery: String): LiveData<ArrayList<MovieResult>> {
        val resultData = MutableLiveData<ArrayList<MovieResult>>()

        apiService.getSearchMovie(searchQuery).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        resultData.postValue(body.results)
                    }
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return resultData
    }

    fun getPopularTv(): LiveData<ArrayList<TvResult>> {
        val resultData = MutableLiveData<ArrayList<TvResult>>()

        apiService.getPopularTv().enqueue(object : Callback<Tv> {
            override fun onResponse(call: Call<Tv>, response: Response<Tv>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        resultData.postValue(body.results)
                    }
                }
            }

            override fun onFailure(call: Call<Tv>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return resultData
    }

    fun getTopRatedTv(): LiveData<ArrayList<TvResult>> {
        val resultData = MutableLiveData<ArrayList<TvResult>>()

        apiService.getTopRatedTv().enqueue(object : Callback<Tv> {
            override fun onResponse(call: Call<Tv>, response: Response<Tv>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        resultData.postValue(body.results)
                    }
                }
            }

            override fun onFailure(call: Call<Tv>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return resultData
    }

    fun getOnTheAirTv(): LiveData<ArrayList<TvResult>> {
        val resultData = MutableLiveData<ArrayList<TvResult>>()

        apiService.getOnTheAirTv().enqueue(object : Callback<Tv> {
            override fun onResponse(call: Call<Tv>, response: Response<Tv>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        resultData.postValue(body.results)
                    }
                }
            }

            override fun onFailure(call: Call<Tv>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return resultData
    }

    fun getAiringTodayTv(): LiveData<ArrayList<TvResult>> {
        val resultData = MutableLiveData<ArrayList<TvResult>>()

        apiService.getAiringTodayTv().enqueue(object : Callback<Tv> {
            override fun onResponse(call: Call<Tv>, response: Response<Tv>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        resultData.postValue(body.results)
                    }
                }
            }

            override fun onFailure(call: Call<Tv>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return resultData
    }

    fun getSearchTv(searchQuery: String): LiveData<ArrayList<TvResult>> {
        val resultData = MutableLiveData<ArrayList<TvResult>>()

        apiService.getSearchTv(searchQuery).enqueue(object : Callback<Tv> {
            override fun onResponse(call: Call<Tv>, response: Response<Tv>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        resultData.postValue(body.results)
                    }
                }
            }

            override fun onFailure(call: Call<Tv>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return resultData
    }
}