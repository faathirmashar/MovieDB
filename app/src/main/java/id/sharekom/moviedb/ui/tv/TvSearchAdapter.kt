package id.sharekom.moviedb.ui.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.sharekom.moviedb.BuildConfig
import id.sharekom.moviedb.R
import id.sharekom.moviedb.data.local.MovieTvEntity
import id.sharekom.moviedb.data.models.TvResult
import id.sharekom.moviedb.databinding.ItemMovieTvBinding

class TvSearchAdapter : RecyclerView.Adapter<TvSearchAdapter.TvSearchViewHolder>() {
    private lateinit var onItemClickListener: OnItemClickListener
    private var listData = ArrayList<TvResult>()

    fun setData(listData: ArrayList<TvResult>) {
        this.listData = listData
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvSearchViewHolder {
        val view = ItemMovieTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvSearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvSearchViewHolder, position: Int) {
        holder.bind(listData[position], onItemClickListener)
    }

    override fun getItemCount(): Int = listData.size

    inner class TvSearchViewHolder(private val binding: ItemMovieTvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvResult: TvResult, onItemClickListener: OnItemClickListener) {
            binding.apply {
                Glide.with(root.context)
                    .load(BuildConfig.IMG_URL + tvResult.poster_path)
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_broken_image)
                    .into(binding.image)

                title.text = root.context.getString(R.string.title, tvResult.original_name)
                releaseDate.text =
                    root.context.getString(R.string.release_date, tvResult.first_air_date)
                averageRating.text =
                    root.context.getString(R.string.average_rating, "%.1f".format(tvResult.vote_average))

                val movieTvEntity = MovieTvEntity(
                    tvResult.backdrop_path ?: "",
                    tvResult.id ?: 0,
                    tvResult.original_name ?: "",
                    tvResult.overview ?: "",
                    tvResult.popularity ?: 0.0,
                    tvResult.poster_path ?: "",
                    tvResult.first_air_date ?: "",
                    tvResult.vote_average ?: 0.0,
                    tvResult.vote_count ?: 0,
                    "tv"
                )

                root.setOnClickListener { onItemClickListener.onItemClicked(movieTvEntity) }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(movieTvEntity: MovieTvEntity)
    }
}