package com.shahin.deezer.ui.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.shahin.deezer.R
import com.shahin.deezer.data.models.ArtistModel
import com.shahin.deezer.databinding.FragmentSearchBinding
import com.shahin.deezer.ui.fragments.BaseFragment
import com.shahin.deezer.ui.fragments.search.adapter.ArtistsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        viewModel.searchForArtist("Metallica")


        val items = arrayListOf<ArtistModel>()
        for (i in 0..20) {
            items.add(
                ArtistModel(
                    i.toLong(),
                    "Hello $i",
                    i.toString()
                )
            )
        }
        setupRecycler(items)
    }

    private fun initObservers() {
        viewModel.artist.observe(viewLifecycleOwner, {
            setupRecycler(it)
        })
    }

    private fun setupRecycler(list: List<ArtistModel>) {
        binding.recyclerView.adapter = ArtistsAdapter(list) {
            navigateToAlbums()
        }
    }

    private fun navigateToAlbums() {
        findNavController().navigate(
            SearchFragmentDirections.actionFragmentSearchToFragmentAlbums()
        )
    }
}