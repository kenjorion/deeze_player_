package com.example.deeze_project.data.player

import android.media.MediaPlayer

object MusicPlayer : playerAdapter {

    lateinit var player: MediaPlayer

    override fun loadTrack(url: String) {
        TODO("Not yet implemented")
    }

    override fun release() {
        TODO("Not yet implemented")
    }

    override fun isPlaying(): Boolean {
        TODO("Not yet implemented")
    }

    override fun play() {
        TODO("Not yet implemented")
    }

    override fun reset() {
        TODO("Not yet implemented")
    }

    override fun pause() {
<<<<<<< Updated upstream
        TODO("Not yet implemented")
=======
        if (player.isPlaying) {
            player.pause()
            currentState = PlayerState.PAUSED
            //synchro player position
        }
    }

    fun seekTo(position: Int) {
        player.seekTo(position)
>>>>>>> Stashed changes
    }


}