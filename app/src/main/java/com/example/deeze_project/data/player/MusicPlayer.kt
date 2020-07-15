package com.example.deeze_project.data.player

import android.media.MediaPlayer

object MusicPlayer : playerAdapter {

    private lateinit var player: MediaPlayer
    private lateinit var currentState: PlayerState
    val currentPosition get() = player.currentPosition


    override fun loadTrack(url: String) {
        player.reset()
        player.setDataSource(url)
        player.prepareAsync()
    }

    override fun release() {
        player.reset()
        currentState = PlayerState.RESET
    }

    override fun isPlaying(): Boolean {
        return player.isPlaying
    }

    override fun play() {
        if(!player.isPlaying) {
            player.start()
            currentState = PlayerState.PLAYING
            //Synchro player position
        }
    }

    override fun reset() {
        player.reset()
        currentState = PlayerState.RESET
    }

    override fun pause() {
        if (player.isPlaying) {
            player.pause()
            currentState = PlayerState.PAUSED
            //synchro player position
        }
    }

    override fun seekTo(position: Int) {
        player.seekTo(position)
    }



}