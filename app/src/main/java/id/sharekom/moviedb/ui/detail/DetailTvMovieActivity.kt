package id.sharekom.moviedb.ui.detail

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import id.sharekom.moviedb.BuildConfig
import id.sharekom.moviedb.R
import id.sharekom.moviedb.ViewModelFactory
import id.sharekom.moviedb.data.local.MovieTvEntity
import id.sharekom.moviedb.databinding.ActivityDetailTvMovieBinding
import id.sharekom.moviedb.ui.MovieTvViewModel

class DetailTvMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTvMovieBinding

    private lateinit var viewModel: MovieTvViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MovieTvViewModel::class.java]

        val intentExtra = intent.getParcelableExtra<MovieTvEntity>(DETAIL_DATA) as MovieTvEntity

        binding.tvTitle.text = intentExtra.title
        setImage(intentExtra.backdropPath, binding.ivBackdrop)
        setImage(intentExtra.posterPath, binding.ivPoster)

        binding.averageRating.text = getString(R.string.average_rating, "%.1f".format(intentExtra.voteAverage))
        binding.tvOverviews.text = intentExtra.overview
        binding.tvReleasedate.text = getString(R.string.release_date, intentExtra.releaseDate)
        binding.tvRatecount.text = intentExtra.voteCount.toString()
        binding.tvPopularity.text = intentExtra.popularity.toString()
        binding.tvType.text = intentExtra.type

        viewModel.getFavoriteById(intentExtra.movieId).observe(this) { data ->
            watchListIconState(binding.fabFavorite, data.isEmpty(), intentExtra)
        }
    }

    private fun setImage(url: String, imgView: ImageView) {
        Glide.with(this)
            .load(BuildConfig.IMG_URL + url)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_broken_image)
            .into(imgView)
    }

    private fun watchListIconState(fabFavorite: FloatingActionButton, state: Boolean, movieTvEntity: MovieTvEntity) {
        if (state) {
            fabFavorite.imageTintList = ColorStateList.valueOf(Color.rgb(170, 170, 170))
            binding.fabFavorite.setOnClickListener {
                viewModel.addToFavorite(movieTvEntity)
            }
        } else {
            fabFavorite.imageTintList = ColorStateList.valueOf(Color.rgb(204, 0, 0))
            binding.fabFavorite.setOnClickListener {
                viewModel.deleteFavorite(movieTvEntity)
            }
        }
    }

    companion object {
        const val DETAIL_DATA = "detail_data"
    }
}