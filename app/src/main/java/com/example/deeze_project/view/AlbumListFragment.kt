package com.example.deeze_project.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deeze_project.R
import com.example.deeze_project.data.api.AlbumAPI
import com.example.deeze_project.data.repo.AlbumRepo
import com.example.deeze_project.viewmodel.albumList.albumListViewModel
import kotlinx.android.synthetic.main.album_list_fragment.*

class AlbumListFragment : Fragment(), ClickListener {
    private lateinit var viewModel: albumListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.album_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api = AlbumAPI()
        val repository = AlbumRepo(api)
        val viewModel by lazy { ViewModelProvider(this).get(albumListViewModel(repository)::class.java) }

        // Recupère les albums (api request)
        viewModel.getAlbums()

        // liveData observable => gere le cycle de vie et protege les données
        // ex: rotation de l'ecran, recevoir un appel, perte de batterie, les données sont pas perdus et recréer grace a LiveData
        viewModel.albums.observe(viewLifecycleOwner, Observer { albums ->
            // album_rcv = id recyclerview dans XML <=> comme findviewbyid
            album_rc.also {
                // init recyclerview
                it.layoutManager = GridLayoutManager(requireContext(), 3)
                it.setHasFixedSize(true)
                it.adapter = AlbumAdapter(albums, this)
            }
        })

    }

    override fun <Album> onItemClick(view: View, data: Album) {
        //Toast.makeText(requireContext(), "Album: " + album.title, Toast.LENGTH_SHORT).show()
        view.let { this.findNavController().navigate(R.id.albumDetailFragment) }
    }
}