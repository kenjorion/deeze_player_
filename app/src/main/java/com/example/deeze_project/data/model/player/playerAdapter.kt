package com.example.deeze_project.data.model.player

interface playerAdapter {

    fun play()

    fun reset()

    fun pause()

    fun loadTrack(url: String)

    fun release()

    fun isPlaying(): Boolean

    fun initializeProgressCallback()

    fun seekTo(position: Int)
}

enum class PlayerState {
    INIT,
    PLAYING,
    PAUSED,
    RESET,
    FINISH,
    RELEASED
}