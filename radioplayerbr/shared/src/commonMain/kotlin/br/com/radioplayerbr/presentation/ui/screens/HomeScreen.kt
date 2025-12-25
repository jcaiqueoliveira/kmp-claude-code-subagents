package br.com.radioplayerbr.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.radioplayerbr.presentation.RadioPlayerViewModel
import br.com.radioplayerbr.presentation.ui.components.MiniPlayer
import br.com.radioplayerbr.presentation.ui.components.RadioStationList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: RadioPlayerViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val playbackInfo by viewModel.playbackInfo.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    var showSearchBar by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // Search Bar
        if (showSearchBar) {
            SearchBar(
                query = searchQuery,
                onQueryChange = { query ->
                    searchQuery = query
                    viewModel.searchStations(query)
                },
                onSearch = { viewModel.searchStations(searchQuery) },
                active = showSearchBar,
                onActiveChange = { showSearchBar = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text("Buscar rádios...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") }
            ) {
                // Search results will appear in the main list
            }
        } else {
            Button(
                onClick = { showSearchBar = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Icon(Icons.Default.Search, contentDescription = "Buscar")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Buscar rádios")
            }
        }

        // Filter chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                selected = uiState.showFavoritesOnly,
                onClick = { viewModel.showFavoritesOnly(!uiState.showFavoritesOnly) },
                label = { Text("Favoritos") }
            )
        }

        // Radio Station List
        Box(modifier = Modifier.weight(1f)) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize()
                )
            } else {
                RadioStationList(
                    stations = uiState.filteredStations,
                    onStationClick = { station ->
                        viewModel.playStation(station)
                    },
                    onFavoriteClick = { station ->
                        viewModel.toggleFavorite(station.id)
                    },
                    currentPlayingId = playbackInfo.currentStation?.id
                )
            }
        }

        // Mini Player
        playbackInfo.currentStation?.let { station ->
            MiniPlayer(
                station = station,
                playbackInfo = playbackInfo,
                onPlayPauseClick = {
                    if (playbackInfo.state is br.com.radioplayerbr.data.model.PlayerState.Playing) {
                        viewModel.pausePlayback()
                    } else {
                        viewModel.playStation(station)
                    }
                },
                onStopClick = { viewModel.stopPlayback() }
            )
        }
    }
}
