package br.com.radioplayerbr.data.model

sealed class PlayerState {
    data object Idle : PlayerState()
    data object Loading : PlayerState()
    data object Playing : PlayerState()
    data object Paused : PlayerState()
    data class Error(val message: String) : PlayerState()
}

data class PlaybackInfo(
    val currentStation: RadioStation? = null,
    val state: PlayerState = PlayerState.Idle,
    val metadata: String? = null
)
