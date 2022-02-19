package id.sharekom.moviedb.di

import android.content.Context
import id.sharekom.moviedb.data.MoviesTvRepository
import id.sharekom.moviedb.data.local.LocalDataSource
import id.sharekom.moviedb.data.local.MovieDatabase
import id.sharekom.moviedb.data.remote.ApiConfig
import id.sharekom.moviedb.data.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): MoviesTvRepository {
        val db = MovieDatabase.getDatabase(context)
        val remoteRepository = RemoteDataSource.getInstance(ApiConfig.getApiService())
        val localRepository = LocalDataSource.getInstance(db)

        return MoviesTvRepository.getInstance(remoteRepository, localRepository)
    }
}
