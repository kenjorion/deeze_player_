package com.example.deeze_project.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.deeze_project.R
import com.example.deeze_project.data.model.Album
import com.example.deeze_project.databinding.ItemRecyclerAlbumBinding
import com.facebook.shimmer.ShimmerFrameLayout
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class AlbumAdapter (
    private val albums: List<Album>,
    private val listener: ClickListener
    ) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    private fun loadImage(imageView: ImageView, url: String, shimmer: ShimmerFrameLayout) {
        shimmer.startShimmerAnimation()
        Picasso.get()
            .load(url)
            .resize(100, 100)
            .centerInside()
            .noFade()
            .placeholder(R.color.grey)
            .error(R.drawable.ic_launcher_foreground)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    imageView.alpha = 0f
                    shimmer.stopShimmerAnimation()
                    imageView.animate().setDuration(1000).alpha(1f).start()
                }

                override fun onError(e: Exception?) {
                    shimmer.stopShimmerAnimation()
                }
            })
    }

    override fun getItemCount() = albums.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : AlbumViewHolder {
        val viewBinding = DataBindingUtil.inflate<ItemRecyclerAlbumBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_recycler_album,
            parent,
            false
        )
        return AlbumViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        with(holder) {
            albumBinding.album = albums[position]

            loadImage(
                albumBinding.albumCover,
                albumBinding.album!!.cover,
                albumBinding.parentShimmerLayout
            )
            albumBinding.root.setOnClickListener {
                listener.onItemClick(albumBinding.root, albums[position])
            }
        }    }


    class AlbumViewHolder(
        val albumBinding: ItemRecyclerAlbumBinding
    ) : RecyclerView.ViewHolder(albumBinding.root) {
    }
    
}