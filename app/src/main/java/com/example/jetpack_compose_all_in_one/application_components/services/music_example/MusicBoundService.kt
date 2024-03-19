package com.example.jetpack_compose_all_in_one.application_components.services.music_example

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.provider.Settings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A bound service for managing music playback.
 */
class MusicBoundService : Service() {

    // Lazy initialization of MediaPlayer instance
    private val mediaPlayer by lazy {
        // Create a MediaPlayer instance with default ringtone URI
        MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI).apply {
            // Set listener to stop service when playback completes
            setOnCompletionListener { stopSelf() }
        }
    }

    // Binder instance for allowing interaction with the service
    private val binder = RemoteControl()

    // Job instance for handling pausing of music
    private var pauseJob: Job? = null

    /**
     * Binder class for this service.
     */
    inner class RemoteControl : Binder() {
        // Access to the parent service
        val service get() = this@MusicBoundService
    }

    /**
     * Called when a client binds to the service. Returns the binder instance.
     */
    override fun onBind(intent: Intent?) = binder

    /**
     * Called when the service is destroyed. Releases MediaPlayer resources.
     */
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    /**
     * Starts playing music from the provided URI.
     * @param musicUri The URI of the music to be played.
     */
    fun startMusic(musicUri: Uri) {
        mediaPlayer.apply {
            // Reset the MediaPlayer instance
            reset()
            // Set data source to the provided URI
            setDataSource(this@MusicBoundService, musicUri)
            // Prepare the MediaPlayer asynchronously
            prepare()
            // Start playback
            start()
        }
    }

    /**
     * Stops the currently playing music.
     */
    fun stopMusic() {
        mediaPlayer.stop()
    }

    /**
     * Pauses the currently playing music, optionally for a specified duration.
     * @param duration The duration for which to pause the music, in milliseconds.
     */
    fun pauseMusic(duration: Long? = null) {
        // Cancel any previous pause job
        pauseJob?.cancel()
        pauseJob = null

        // Start a new coroutine for pausing the music
        pauseJob = CoroutineScope(Dispatchers.Default + Job()).launch {
            mediaPlayer.pause()
            // If duration is specified, delay for that duration and then resume music
            duration?.let {
                delay(it)
                resumeMusic()
            }
        }
    }

    /**
     * Resumes playback of paused music.
     */
    fun resumeMusic() {
        mediaPlayer.start()
        // Cancel any ongoing pause job
        pauseJob?.cancel()
        pauseJob = null
    }
}
