package br.com.radioplayerbr.data.repository

import br.com.radioplayerbr.data.model.RadioStation
import br.com.radioplayerbr.data.remote.RadioApiService
import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class RadioRepository(
    private val apiService: RadioApiService = RadioApiService(),
    private val settings: Settings
) {
    private val _radioStations = MutableStateFlow<List<RadioStation>>(emptyList())
    val radioStations: Flow<List<RadioStation>> = _radioStations

    private val favoritesKey = "favorites"

    suspend fun loadRadioStations() {
        try {
            val stations = apiService.getRadioStations()
            val favoriteIds = getFavoriteIds()
            val stationsWithFavorites = stations.map { station ->
                station.copy(isFavorite = favoriteIds.contains(station.id))
            }
            _radioStations.value = stationsWithFavorites
        } catch (e: Exception) {
            // Handle error
            println("Error loading radio stations: ${e.message}")
        }
    }

    suspend fun searchStations(query: String): List<RadioStation> {
        return if (query.isBlank()) {
            _radioStations.value
        } else {
            val results = apiService.searchRadioStations(query)
            val favoriteIds = getFavoriteIds()
            results.map { station ->
                station.copy(isFavorite = favoriteIds.contains(station.id))
            }
        }
    }

    fun getFavorites(): Flow<List<RadioStation>> {
        return _radioStations.map { stations ->
            stations.filter { it.isFavorite }
        }
    }

    fun toggleFavorite(stationId: String) {
        val currentStations = _radioStations.value.toMutableList()
        val index = currentStations.indexOfFirst { it.id == stationId }

        if (index != -1) {
            val station = currentStations[index]
            val updatedStation = station.copy(isFavorite = !station.isFavorite)
            currentStations[index] = updatedStation
            _radioStations.value = currentStations

            // Persist favorites
            saveFavoriteIds(currentStations.filter { it.isFavorite }.map { it.id })
        }
    }

    private fun getFavoriteIds(): Set<String> {
        val favoritesJson = settings.getStringOrNull(favoritesKey) ?: return emptySet()
        return try {
            Json.decodeFromString<Set<String>>(favoritesJson)
        } catch (e: Exception) {
            emptySet()
        }
    }

    private fun saveFavoriteIds(ids: List<String>) {
        val favoritesJson = Json.encodeToString(ids.toSet())
        settings.putString(favoritesKey, favoritesJson)
    }

    fun getStationById(id: String): RadioStation? {
        return _radioStations.value.find { it.id == id }
    }
}
