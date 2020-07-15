package com.example.deeze_project.viewmodel.albumDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deeze_project.data.model.Track
import com.example.deeze_project.data.player.MusicPlayer
import com.example.deeze_project.data.repo.TrackRepo
import kotlinx.coroutines.*

class albumDetailViewModel(
    private val repository: TrackRepo
): ViewModel() {

    private val tracksMutable = MutableLiveData<List<Track>>()
    val tracks: LiveData<List<Track>> get() = tracksMutable

    private val playerAdapterPrivate = MusicPlayer
    val playerAdapter get() = playerAdapterPrivate
    private val currentTrackMutable = MutableLiveData<Track?>()
    val currentTrack: LiveData<Track?> get() = currentTrackMutable

    private val errorlMutableLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable> = errorlMutableLiveData



     fun getTracks(albumID: Int) {
        var job = CoroutineScope(Dispatchers.Main).launch {
            val data = async {
                return@async repository.getTracks(albumID)
            }.await()
            tracksMutable.value
        }
    }

    fun setCurrentTrack(track: Track) {
        currentTrackMutable.value = track
    }

}