package id.sharekom.moviedb.ui.movie

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import id.sharekom.moviedb.data.local.MovieTvEntity
import id.sharekom.moviedb.databinding.DialogSearchBinding
import id.sharekom.moviedb.ui.MovieTvViewModel
import id.sharekom.moviedb.ui.detail.DetailTvMovieActivity

class MovieSearchDialog private constructor(context: Context, private val searchQuery: String, private val viewModel: MovieTvViewModel, private val viewLifecycleOwner: LifecycleOwner): Dialog(context),
    MovieSearchAdapter.OnItemClickListener {
    private lateinit var binding: DialogSearchBinding
    private lateinit var adapter: MovieSearchAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MovieSearchAdapter()
        adapter.setOnItemClickListener(this)

        window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT)

        binding.pbDialogSearch.visibility = View.VISIBLE
        if (searchQuery.isEmpty()) {
            adapter.setData(ArrayList())
            adapter.notifyDataSetChanged()
        } else {
            viewModel.getSearchMovie(searchQuery).observe(viewLifecycleOwner) { results ->
                binding.pbDialogSearch.visibility = View.INVISIBLE
                adapter.setData(results)
                adapter.notifyDataSetChanged()
            }
        }

        binding.btnClose.setOnClickListener { this.dismiss() }
        binding.rvSearch.layoutManager = LinearLayoutManager(context)
        binding.rvSearch.setHasFixedSize(true)
        binding.rvSearch.adapter = adapter
    }

    class Builder(private val context: Context) {
        private lateinit var searchQuery: String
        private lateinit var viewModel: MovieTvViewModel
        private lateinit var viewLifecycleOwner: LifecycleOwner

        fun setSearchQuery(searchQuery: String): Builder {
            this.searchQuery = searchQuery
            return this
        }

        fun setViewModel(viewModel: MovieTvViewModel): Builder {
            this.viewModel = viewModel
            return this
        }

        fun setViewLifecycleOwner(viewLifecycleOwner: LifecycleOwner): Builder {
            this.viewLifecycleOwner = viewLifecycleOwner
            return this
        }

        fun show() = MovieSearchDialog(context, searchQuery, viewModel, viewLifecycleOwner).show()
    }

    override fun onItemClicked(movieTvEntity: MovieTvEntity) {
        val intent = Intent(context, DetailTvMovieActivity::class.java)

        intent.putExtra(DetailTvMovieActivity.DETAIL_DATA, movieTvEntity)

        context.startActivity(intent)
    }
}