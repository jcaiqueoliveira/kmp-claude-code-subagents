package br.com.radioplayerbr.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RadioStationDto(
    @SerialName("stationuuid")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("url")
    val streamUrl: String,

    @SerialName("favicon")
    val imageUrl: String? = null,

    @SerialName("tags")
    val tags: String? = null,

    @SerialName("country")
    val country: String? = null,

    @SerialName("countrycode")
    val countryCode: String? = null,

    @SerialName("state")
    val state: String? = null,

    @SerialName("language")
    val language: String? = null,

    @SerialName("votes")
    val votes: Int = 0,

    @SerialName("bitrate")
    val bitrate: Int? = null,

    @SerialName("homepage")
    val website: String? = null,

    @SerialName("codec")
    val codec: String? = null
)
