package id.sharekom.moviedb.ui.watch_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.sharekom.moviedb.BuildConfig
import id.sharekom.moviedb.R
import id.sharekom.moviedb.data.local.MovieTvEntity
import id.sharekom.moviedb.databinding.ItemMovieTvBinding

class WatchListAdapter : RecyclerView.Adapter<WatchListAdapter.WatchListViewHolder>() {
    private lateinit var onItemClickListener: OnItemClickListener
    private var listData = ArrayList<MovieTvEntity>()

    fun setData(listData: ArrayList<MovieTvEntity>) {
        this.listData = listData
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchListViewHolder {
        val view = ItemMovieTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WatchListViewHolder(view)
    }

    override fun onBindViewHolder(holder: WatchListViewHolder, position: Int) {
        holder.bind(listData[position], onItemClickListener)
    }

    override fun getItemCount(): Int = listData.size

    inner class WatchListViewHolder(private val binding: ItemMovieTvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieTvEntity: MovieTvEntity, onItemClickListener: OnItemClickListener) {
            binding.apply {
                Glide.with(root.context)
                    .load(BuildConfig.IMG_URL + movieTvEntity.posterPath)
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_broken_image)
                    .into(binding.image)

                title.text = root.context.getString(R.string.title, movieTvEntity.title)
                releaseDate.text =
                    root.context.getString(R.string.release_date, movieTvEntity.releaseDate)
                averageRating.text =
                    root.context.getString(R.string.average_rating, "%.1f".format(movieTvEntity.voteAverage))

                root.setOnClickListener { onItemClickListener.onItemClicked(movieTvEntity) }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(movieTvEntity: MovieTvEntity)
    }
}