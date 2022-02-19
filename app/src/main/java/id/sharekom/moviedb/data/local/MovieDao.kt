package id.sharekom.moviedb.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getMovies(): LiveData<List<MovieTvEntity>>

    @Query("SELECT * from movies WHERE movie_id=:id")
    fun favoriteById(id: Int): LiveData<List<MovieTvEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovie(movies: MovieTvEntity)

    @Delete
    fun deleteFavoriteMovie(movie: MovieTvEntity)
}
