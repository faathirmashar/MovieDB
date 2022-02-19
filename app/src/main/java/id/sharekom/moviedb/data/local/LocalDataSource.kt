package id.sharekom.moviedb.data.local

import androidx.lifecycle.LiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class LocalDataSource private constructor(private val movieDatabase: MovieDatabase) {
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    companion object {
        @Volatile
        private var instance: LocalDataSource? = null

        fun getInstance(movieDatabase: MovieDatabase): LocalDataSource =
            instance ?: synchronized(this) {
                LocalDataSource(movieDatabase).apply { instance = this }
            }
    }

    fun getMovies(): LiveData<List<MovieTvEntity>> = movieDatabase.movieDao().getMovies()

    fun getFavoriteById(id: Int): LiveData<List<MovieTvEntity>> = movieDatabase.movieDao().favoriteById(id)

    fun insertMovie(movieEntity: MovieTvEntity){
        executorService.execute{ movieDatabase.movieDao().insertMovie(movieEntity) }
    }

    fun deleteMovie(movieEntity: MovieTvEntity){
        executorService.execute { movieDatabase.movieDao().deleteFavoriteMovie(movieEntity) }
    }
}