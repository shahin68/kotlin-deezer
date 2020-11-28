package com.shahin.deezer.ui.fragments.artist.tracks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.shahin.deezer.R
import com.shahin.deezer.databinding.FragmentTracksBinding
import com.shahin.deezer.ui.fragments.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TracksFragment : BaseFragment<FragmentTracksBinding>(R.layout.fragment_tracks) {

    private val viewModel: TracksViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTracksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchTracks()
    }

}