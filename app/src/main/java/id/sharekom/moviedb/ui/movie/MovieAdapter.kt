package id.sharekom.moviedb.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.sharekom.moviedb.BuildConfig
import id.sharekom.moviedb.R
import id.sharekom.moviedb.data.local.MovieTvEntity
import id.sharekom.moviedb.data.models.MovieResult
import id.sharekom.moviedb.databinding.ItemMovieTvBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private lateinit var onItemClickListener: OnItemClickListener
    private var listData = ArrayList<MovieResult>()

    fun setData(listData: ArrayList<MovieResult>) {
        this.listData = listData
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemMovieTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listData[position], onItemClickListener)
    }

    override fun getItemCount(): Int = listData.size

    inner class MovieViewHolder(private val binding: ItemMovieTvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieResult: MovieResult, onItemClickListener: OnItemClickListener) {
            binding.apply {
                Glide.with(root.context)
                    .load(BuildConfig.IMG_URL + movieResult.poster_path)
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_broken_image)
                    .into(binding.image)

                title.text = root.context.getString(R.string.title, movieResult.original_title)
                releaseDate.text =
                    root.context.getString(R.string.release_date, movieResult.release_date)
                averageRating.text =
                    root.context.getString(R.string.average_rating, "%.1f".format(movieResult.vote_average))

                val movieTvEntity = MovieTvEntity(
                    movieResult.backdrop_path ?: "",
                    movieResult.id ?: 0,
                    movieResult.original_title ?: "",
                    movieResult.overview ?: "",
                    movieResult.popularity ?: 0.0,
                    movieResult.poster_path ?: "",
                    movieResult.release_date ?: "",
                    movieResult.vote_average ?: 0.0,
                    movieResult.vote_count ?: 0,
                    "movie"
                )

                root.setOnClickListener { onItemClickListener.onItemClicked(movieTvEntity) }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(movieTvEntity: MovieTvEntity)
    }
}