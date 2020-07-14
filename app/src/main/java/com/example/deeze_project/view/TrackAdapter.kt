package com.example.deeze_project.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.deeze_project.R
import com.example.deeze_project.data.model.Track
import com.example.deeze_project.databinding.ItemRecyclerTrackBinding

class TrackAdapter (
    private val tracks: List<Track>,
    private val listener: ClickListener
) : RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val viewBinding = DataBindingUtil.inflate<ItemRecyclerTrackBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_recycler_track,
            parent,
            false
        )
        return TrackViewHolder(viewBinding)
    }

    override fun getItemCount() = tracks.size

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        with(holder) {
            recyclerViewTrackBinding.track = tracks[position]

            recyclerViewTrackBinding.root.setOnClickListener {
                listener.onItemClick(recyclerViewTrackBinding.root, tracks[position])
            }
        }
    }

    class TrackViewHolder (
        val recyclerViewTrackBinding: ItemRecyclerTrackBinding
    ) : RecyclerView.ViewHolder(recyclerViewTrackBinding.root) {
    }
}