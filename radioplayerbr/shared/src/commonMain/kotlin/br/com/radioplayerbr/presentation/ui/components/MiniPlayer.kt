package br.com.radioplayerbr.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.com.radioplayerbr.data.model.PlaybackInfo
import br.com.radioplayerbr.data.model.PlayerState
import br.com.radioplayerbr.data.model.RadioStation

@Composable
fun MiniPlayer(
    station: RadioStation,
    playbackInfo: PlaybackInfo,
    onPlayPauseClick: () -> Unit,
    onStopClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = station.name,
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    val stateText = when (playbackInfo.state) {
                        is PlayerState.Loading -> "Carregando..."
                        is PlayerState.Playing -> "Tocando agora"
                        is PlayerState.Paused -> "Pausado"
                        is PlayerState.Error -> (playbackInfo.state as PlayerState.Error).message
                        else -> ""
                    }

                    Text(
                        text = stateText,
                        style = MaterialTheme.typography.bodySmall,
                        color = if (playbackInfo.state is PlayerState.Error) {
                            MaterialTheme.colorScheme.error
                        } else {
                            MaterialTheme.colorScheme.onSecondaryContainer
                        }
                    )
                }

                // Play/Pause Button
                IconButton(
                    onClick = onPlayPauseClick,
                    enabled = playbackInfo.state !is PlayerState.Loading
                ) {
                    Icon(
                        imageVector = if (playbackInfo.state is PlayerState.Playing) {
                            Icons.Default.Pause
                        } else {
                            Icons.Default.PlayArrow
                        },
                        contentDescription = if (playbackInfo.state is PlayerState.Playing) {
                            "Pausar"
                        } else {
                            "Reproduzir"
                        }
                    )
                }

                // Stop Button
                IconButton(
                    onClick = onStopClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Stop,
                        contentDescription = "Parar"
                    )
                }
            }

            // Loading indicator
            if (playbackInfo.state is PlayerState.Loading) {
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
