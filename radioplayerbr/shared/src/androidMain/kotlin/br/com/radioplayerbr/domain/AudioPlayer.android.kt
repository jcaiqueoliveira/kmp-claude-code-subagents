package br.com.radioplayerbr.domain

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import br.com.radioplayerbr.data.model.PlayerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

actual class AudioPlayer(private val context: Context) {
    private var exoPlayer: ExoPlayer? = null
    private val _playerState = MutableStateFlow<PlayerState>(PlayerState.Idle)
    actual val playerState: StateFlow<PlayerState> = _playerState.asStateFlow()

    init {
        initializePlayer()
    }

    private fun initializePlayer() {
        exoPlayer = ExoPlayer.Builder(context).build().apply {
            addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(playbackState: Int) {
                    _playerState.value = when (playbackState) {
                        Player.STATE_IDLE -> PlayerState.Idle
                        Player.STATE_BUFFERING -> PlayerState.Loading
                        Player.STATE_READY -> if (isPlaying) PlayerState.Playing else PlayerState.Paused
                        Player.STATE_ENDED -> PlayerState.Idle
                        else -> PlayerState.Idle
                    }
                }

                override fun onPlayerError(error: PlaybackException) {
                    _playerState.value = PlayerState.Error(error.message ?: "Erro ao reproduzir")
                }

                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    if (playbackState == Player.STATE_READY) {
                        _playerState.value = if (isPlaying) PlayerState.Playing else PlayerState.Paused
                    }
                }
            })
        }
    }

    actual fun play(streamUrl: String) {
        exoPlayer?.let { player ->
            val mediaItem = MediaItem.fromUri(streamUrl)
            player.setMediaItem(mediaItem)
            player.prepare()
            player.playWhenReady = true
        }
    }

    actual fun pause() {
        exoPlayer?.pause()
    }

    actual fun stop() {
        exoPlayer?.stop()
        _playerState.value = PlayerState.Idle
    }

    actual fun release() {
        exoPlayer?.release()
        exoPlayer = null
        _playerState.value = PlayerState.Idle
    }

    actual fun isPlaying(): Boolean {
        return exoPlayer?.isPlaying == true
    }
}
