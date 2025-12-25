package br.com.radioplayerbr.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.radioplayerbr.data.model.PlaybackInfo
import br.com.radioplayerbr.data.model.PlayerState
import br.com.radioplayerbr.data.model.RadioStation
import br.com.radioplayerbr.data.repository.RadioRepository
import br.com.radioplayerbr.domain.AudioPlayer
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RadioPlayerViewModel(
    private val repository: RadioRepository,
    private val audioPlayer: AudioPlayer
) : ViewModel() {

    private val _uiState = MutableStateFlow(RadioPlayerUiState())
    val uiState: StateFlow<RadioPlayerUiState> = _uiState.asStateFlow()

    private val _playbackInfo = MutableStateFlow(PlaybackInfo())
    val playbackInfo: StateFlow<PlaybackInfo> = _playbackInfo.asStateFlow()

    init {
        loadRadioStations()
        observePlayerState()
    }

    private fun loadRadioStations() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            repository.loadRadioStations()
            repository.radioStations.collect { stations ->
                _uiState.value = _uiState.value.copy(
                    radioStations = stations,
                    filteredStations = stations,
                    isLoading = false
                )
            }
        }
    }

    private fun observePlayerState() {
        viewModelScope.launch {
            audioPlayer.playerState.collect { state ->
                _playbackInfo.value = _playbackInfo.value.copy(state = state)
            }
        }
    }

    fun playStation(station: RadioStation) {
        audioPlayer.play(station.streamUrl)
        _playbackInfo.value = _playbackInfo.value.copy(
            currentStation = station,
            state = PlayerState.Loading
        )
    }

    fun pausePlayback() {
        audioPlayer.pause()
    }

    fun stopPlayback() {
        audioPlayer.stop()
        _playbackInfo.value = PlaybackInfo()
    }

    fun toggleFavorite(stationId: String) {
        repository.toggleFavorite(stationId)
    }

    fun searchStations(query: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(searchQuery = query)
            val results = repository.searchStations(query)
            _uiState.value = _uiState.value.copy(filteredStations = results)
        }
    }

    fun filterByGenre(genre: String?) {
        viewModelScope.launch {
            val filtered = if (genre == null) {
                _uiState.value.radioStations
            } else {
                _uiState.value.radioStations.filter { it.genre == genre }
            }
            _uiState.value = _uiState.value.copy(
                selectedGenre = genre,
                filteredStations = filtered
            )
        }
    }

    fun showFavoritesOnly(show: Boolean) {
        viewModelScope.launch {
            val filtered = if (show) {
                _uiState.value.radioStations.filter { it.isFavorite }
            } else {
                _uiState.value.radioStations
            }
            _uiState.value = _uiState.value.copy(
                showFavoritesOnly = show,
                filteredStations = filtered
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        audioPlayer.release()
    }
}

data class RadioPlayerUiState(
    val radioStations: List<RadioStation> = emptyList(),
    val filteredStations: List<RadioStation> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val selectedGenre: String? = null,
    val showFavoritesOnly: Boolean = false
)
