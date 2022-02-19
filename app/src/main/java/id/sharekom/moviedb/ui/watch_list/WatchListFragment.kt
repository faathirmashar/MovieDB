package id.sharekom.moviedb.ui.watch_list

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.sharekom.moviedb.ViewModelFactory
import id.sharekom.moviedb.data.local.MovieTvEntity
import id.sharekom.moviedb.databinding.FragmentWatchListBinding
import id.sharekom.moviedb.ui.MovieTvViewModel
import id.sharekom.moviedb.ui.detail.DetailTvMovieActivity

class WatchListFragment : Fragment(), WatchListAdapter.OnItemClickListener {
    private var _binding: FragmentWatchListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MovieTvViewModel
    private lateinit var adapter: WatchListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWatchListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory)[MovieTvViewModel::class.java]

        adapter = WatchListAdapter()
        adapter.setOnItemClickListener(this)

        viewModel.getAllFavorite().observe(viewLifecycleOwner) { data ->
            adapter.setData(data as ArrayList<MovieTvEntity>)
            adapter.notifyDataSetChanged()
        }

        binding.rvWatchList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvWatchList.setHasFixedSize(true)
        binding.rvWatchList.adapter = adapter
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
}