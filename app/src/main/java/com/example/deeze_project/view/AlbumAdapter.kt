package com.example.deeze_project.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.deeze_project.R
import com.example.deeze_project.data.model.Album
import com.example.deeze_project.databinding.AlbumItemRecyclerBinding
import com.facebook.shimmer.ShimmerFrameLayout
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class AlbumAdapter (
    private val albums: List<Album>,
    private val listener: ClickListener
    ) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    fun loadImage(imageView: ImageView, url: String, shimmer: ShimmerFrameLayout) {
        shimmer.startShimmerAnimation()
        Picasso.get()
            .load(url)
            .resize(100, 100)
            .centerInside()
            .noFade()
            .placeholder(R.color.grey)
            .error(R.drawable.ic_launcher_background)  //TODO: changer image error
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    // animation fade-in
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
        val viewBinding = DataBindingUtil.inflate<AlbumItemRecyclerBinding>(
            LayoutInflater.from(parent.context),
            R.layout.album_item_recycler,
            parent,
            false
        )
        return AlbumViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        with(holder) {
            albumBinding.album = albums[position]

            loadImage(
                albumBinding.albumCover, //recupere view (albumCover = id dans XML)
                albumBinding.album!!.cover,
                albumBinding.parentShimmerLayout // recupere view (parentShimmerLayout = id dans XML)
            )

            albumBinding.root.setOnClickListener {
                listener.onItemClick(albumBinding.root, albums[position])
            }
        }    }


    class AlbumViewHolder(
        val albumBinding: AlbumItemRecyclerBinding
    ) : RecyclerView.ViewHolder(albumBinding.root) {
    }
    
}