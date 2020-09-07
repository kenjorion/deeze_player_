package com.example.deeze_project.viewmodel.albumList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deeze_project.data.model.Album
import com.example.deeze_project.data.repo.AlbumRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class albumListViewModel(
    private val repository: AlbumRepo
    ) : ViewModel() {

    private val albumsMutable = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> = albumsMutable

    suspend fun getAlbums() {
        var job = CoroutineScope(Dispatchers.Main).launch {
            val data = async {
                return@async repository.getAlbums()
            }.await()
            albumsMutable.value
        }
        repository.getAlbums()
    }
}