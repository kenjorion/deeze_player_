package com.example.deeze_project.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deeze_project.R
import com.example.deeze_project.data.api.TrackAPI
import com.example.deeze_project.data.repo.TrackRepo
import com.example.deeze_project.databinding.AlbumDetailFragmentBinding
import com.example.deeze_project.viewmodel.albumDetail.albumDetailViewModel
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.album_detail_fragment.*
import kotlinx.coroutines.*

class AlbumDetailFragment : Fragment(), ClickListener, SlidingUpPanelLayout.PanelSlideListener {

    private val args by navArgs<AlbumDetailFragmentArgs>()

    val api = TrackAPI()
    val repository = TrackRepo(api)
    private val viewModel by lazy { ViewModelProvider(this).get(albumDetailViewModel(repository)::class.java) }
    private lateinit var binding: AlbumDetailFragmentBinding


    private lateinit var playerPanel: SlidingUpPanelLayout
    private lateinit var playerBar: LinearLayout
    private lateinit var playBtnBar: ImageButton
    private lateinit var pauseBtnBar: ImageButton
    private lateinit var playBtnPlayer: ImageButton
    private lateinit var pauseBtnPlayer: ImageButton
    private lateinit var seekBarAudio: AppCompatSeekBar
    private lateinit var currentPositionPlay: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            val arg = AlbumDetailFragmentArgs.fromBundle(it)
            arg.trackItem.id
        }
        val viewBinding = DataBindingUtil.inflate<AlbumDetailFragmentBinding>(
            inflater,
            R.layout.album_detail_fragment,
            container,
            false
        )
        binding = viewBinding
        viewBinding.album = args.albumItem
        return viewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view) {
            val toolbar: Toolbar = findViewById(R.id.toolbar)
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }

            seekBarAudio = findViewById(R.id.player_content_seekbar)
            currentPositionPlay = findViewById(R.id.player_content_current_pos)
            playBtnBar = findViewById(R.id.player_btn_play)
            playBtnPlayer = findViewById(R.id.player_content_btn_play)
            pauseBtnBar = findViewById(R.id.player_btn_pause)
            pauseBtnPlayer = findViewById(R.id.player_content_btn_pause)
            playerBar = findViewById(R.id.player_bar)
            playerPanel = findViewById(R.id.player_sliding)
            playerPanel.panelState = SlidingUpPanelLayout.PanelState.HIDDEN // caché player bar
            playerPanel.addPanelSlideListener(this@AlbumDetailFragment)

            // Click Listener
            playBtnBar.setOnClickListener { viewModel.playerAdapter.play() }
            playBtnPlayer.setOnClickListener { viewModel.playerAdapter.play() }
            pauseBtnBar.setOnClickListener { viewModel.playerAdapter.pause() }
            pauseBtnPlayer.setOnClickListener { viewModel.playerAdapter.pause() }
            findViewById<ImageButton>(R.id.player_btn_next)
            findViewById<ImageButton>(R.id.player_content_btn_next)
            findViewById<ImageButton>(R.id.player_btn_previous)
            findViewById<ImageButton>(R.id.player_content_btn_previous)
        }

        viewModel.currentTrack.observe(viewLifecycleOwner, Observer { track ->
            binding.track = track
        })

        initObservers()
        viewModel.getTracks(args.albumItem.id)
    }


    fun Fragment.showError(error: Throwable) =
        Toast.makeText(context, error.localizedMessage, Toast.LENGTH_SHORT).show()

    private fun initObservers() {
        viewModel.tracks.observe(viewLifecycleOwner, Observer { data ->
            tracks_rc.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = TrackAdapter(data, this)
            }
        })

        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer { error ->
            showError(error)
        })
    }

    override fun <Track> onItemClick(view: View, data: Track) {
        val track = data as com.example.deeze_project.data.model.Track
        binding.track = track
        viewModel.setCurrentTrack(track)
        viewModel.playerAdapter.loadTrack(track.song)
    }


    override fun onPanelSlide(panel: View?, slideOffset: Float) {
        TODO("Not yet implemented")
    }

    override fun onPanelStateChanged(
        panel: View?,
        previousState: SlidingUpPanelLayout.PanelState?,
        newState: SlidingUpPanelLayout.PanelState?
    ) {
        TODO("Not yet implemented")
    }


}