package br.com.radioplayerbr.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Radio
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.com.radioplayerbr.data.model.RadioStation

@Composable
fun RadioStationList(
    stations: List<RadioStation>,
    onStationClick: (RadioStation) -> Unit,
    onFavoriteClick: (RadioStation) -> Unit,
    currentPlayingId: String?,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(stations, key = { it.id }) { station ->
            RadioStationItem(
                station = station,
                isPlaying = station.id == currentPlayingId,
                onStationClick = { onStationClick(station) },
                onFavoriteClick = { onFavoriteClick(station) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RadioStationItem(
    station: RadioStation,
    isPlaying: Boolean,
    onStationClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onStationClick),
        colors = CardDefaults.cardColors(
            containerColor = if (isPlaying) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surface
            }
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Radio Icon
            Icon(
                imageVector = Icons.Default.Radio,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = if (isPlaying) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface
                }
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Station Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = station.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                station.genre?.let { genre ->
                    Text(
                        text = genre,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                station.city?.let { city ->
                    val location = if (station.state != null) "$city - ${station.state}" else city
                    Text(
                        text = location,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Favorite Button
            IconButton(
                onClick = onFavoriteClick
            ) {
                Icon(
                    imageVector = if (station.isFavorite) {
                        Icons.Default.Favorite
                    } else {
                        Icons.Default.FavoriteBorder
                    },
                    contentDescription = if (station.isFavorite) {
                        "Remover dos favoritos"
                    } else {
                        "Adicionar aos favoritos"
                    },
                    tint = if (station.isFavorite) {
                        MaterialTheme.colorScheme.error
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    }
                )
            }
        }
    }
}
