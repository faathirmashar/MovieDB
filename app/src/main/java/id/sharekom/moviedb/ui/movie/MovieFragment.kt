package id.sharekom.moviedb.ui.movie

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.ChipGroup
import id.sharekom.moviedb.R
import id.sharekom.moviedb.ViewModelFactory
import id.sharekom.moviedb.data.local.MovieTvEntity
import id.sharekom.moviedb.databinding.FragmentMovieBinding
import id.sharekom.moviedb.ui.MovieTvViewModel
import id.sharekom.moviedb.ui.detail.DetailTvMovieActivity

class MovieFragment : Fragment(), MovieAdapter.OnItemClickListener,ChipGroup.OnCheckedChangeListener {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MovieTvViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MovieAdapter()

        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory)[MovieTvViewModel::class.java]
        selectObserver(0)

        val movieSearchDialog = MovieSearchDialog.Builder(requireContext())
            .setViewLifecycleOwner(viewLifecycleOwner)
            .setViewModel(viewModel)

        binding.chipChoiceGroup.setOnCheckedChangeListener(this)

        binding.rvMovie.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMovie.setHasFixedSize(true)
        adapter.setOnItemClickListener(this)
        binding.rvMovie.adapter = adapter

        binding.tiSearch.setEndIconOnClickListener {
            movieSearchDialog.setSearchQuery(binding.tiSearch.editText?.text.toString())
            movieSearchDialog.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    override fun onItemClicked(movieTvEntity: MovieTvEntity) {
        val intent = Intent(requireContext(), DetailTvMovieActivity::class.java)

        intent.putExtra(DetailTvMovieActivity.DETAIL_DATA, movieTvEntity)

        startActivity(intent)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun selectObserver(index: Int) {
        binding.pbMovie.visibility = View.VISIBLE
        when(index){
            0 -> viewModel.getTopRatedMovies().observe(viewLifecycleOwner) { results ->
                binding.pbMovie.visibility = View.INVISIBLE
                adapter.setData(results)
                adapter.notifyDataSetChanged()
            }
            1 -> viewModel.getUpcomingMovies().observe(viewLifecycleOwner) { results ->
                binding.pbMovie.visibility = View.INVISIBLE
                adapter.setData(results)
                adapter.notifyDataSetChanged()
            }
            2 -> viewModel.getNowPlayingMovies().observe(viewLifecycleOwner) { results ->
                binding.pbMovie.visibility = View.INVISIBLE
                adapter.setData(results)
                adapter.notifyDataSetChanged()
            }
            3 -> viewModel.getPopularMovies().observe(viewLifecycleOwner) { results ->
                binding.pbMovie.visibility = View.INVISIBLE
                adapter.setData(results)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCheckedChanged(group: ChipGroup?, checkedId: Int) {
        when(checkedId) {
            R.id.chip_top_rated -> selectObserver(0)
            R.id.chip_upcoming -> selectObserver(1)
            R.id.chip_now_playing -> selectObserver(2)
            R.id.chip_popular -> selectObserver(3)
        }
    }
}