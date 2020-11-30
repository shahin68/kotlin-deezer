package com.shahin.deezer.ui.fragments.artist.tracks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.shahin.deezer.R
import com.shahin.deezer.data.models.TrackModel
import com.shahin.deezer.databinding.FragmentTracksBinding
import com.shahin.deezer.extensions.loadImage
import com.shahin.deezer.ui.fragments.BaseFragment
import com.shahin.deezer.ui.fragments.artist.tracks.adapter.TracksAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TracksFragment : BaseFragment<FragmentTracksBinding>(R.layout.fragment_tracks) {

    private val viewModel: TracksViewModel by viewModels()
    private val args: TracksFragmentArgs by navArgs()

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
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.fetchTracks()

        binding.appBarImage.loadImage(args.cover)
        binding.toolbar.title = args.albumName
        binding.toolbar.subtitle = args.artistName

        val items = arrayListOf<TrackModel>()
        for (i in 0..20) {
            items.add(
                TrackModel(
                    i.toLong(),
                    "Track Title $i",
                    "Artist Name $i"
                )
            )
        }
        setupRecycler(items)
    }

    private fun setupRecycler(list: List<TrackModel>) {
        binding.recyclerView.adapter = TracksAdapter(list) {

        }
    }
}