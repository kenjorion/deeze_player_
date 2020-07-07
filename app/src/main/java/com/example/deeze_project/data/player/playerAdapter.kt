package com.example.deeze_project.data.player

interface playerAdapter {

    fun loadTrack(url: String)

    fun release()

    fun isPlaying(): Boolean

    fun play()

    fun reset()

    fun pause()

    // fun initializeProgressCallback()

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