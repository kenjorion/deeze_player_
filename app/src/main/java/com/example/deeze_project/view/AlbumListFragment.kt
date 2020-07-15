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
import com.example.deeze_project.R
import com.example.deeze_project.data.api.AlbumAPI
import com.example.deeze_project.data.repo.AlbumRepo
import com.example.deeze_project.viewmodel.albumList.albumListViewModel
import kotlinx.android.synthetic.main.album_list_fragment.*

class AlbumListFragment : Fragment(), ClickListener {

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

        viewModel.getAlbums()

        viewModel.albums.observe(viewLifecycleOwner, Observer { albums ->
            album_rc.also {
                it.layoutManager = GridLayoutManager(requireContext(), 3)
                it.setHasFixedSize(true)
                it.adapter = AlbumAdapter(albums, this)
            }
        })

    }

    override fun <Album> onItemClick(view: View, data: Album) {
        view.let { this.findNavController().navigate(R.id.albumDetailFragment) }
    }
}