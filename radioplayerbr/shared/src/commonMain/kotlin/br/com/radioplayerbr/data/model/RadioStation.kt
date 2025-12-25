package br.com.radioplayerbr.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RadioStation(
    val id: String,
    val name: String,
    val streamUrl: String,
    val imageUrl: String? = null,
    val description: String? = null,
    val genre: String? = null,
    val city: String? = null,
    val state: String? = null,
    val country: String = "Brasil",
    val website: String? = null,
    val bitrate: Int? = null,
    var isFavorite: Boolean = false
)

@Serializable
data class RadioStationCategory(
    val name: String,
    val stations: List<RadioStation>
)

enum class RadioGenre(val displayName: String) {
    POP("Pop"),
    ROCK("Rock"),
    SERTANEJO("Sertanejo"),
    MPB("MPB"),
    SAMBA("Samba"),
    FORRO("Forró"),
    GOSPEL("Gospel"),
    NOTICIAS("Notícias"),
    ESPORTES("Esportes"),
    OUTROS("Outros")
}
