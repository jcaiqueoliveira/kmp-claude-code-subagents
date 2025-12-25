package br.com.radioplayerbr.data.remote

import br.com.radioplayerbr.data.model.RadioStation
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class RadioApiService {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
    }

    // Mock data - pode ser substituído por API real (RadioBrowser API ou similar)
    suspend fun getRadioStations(): List<RadioStation> {
        // Simulando delay de rede
        kotlinx.coroutines.delay(500)

        return getMockRadioStations()
    }

    suspend fun searchRadioStations(query: String): List<RadioStation> {
        kotlinx.coroutines.delay(300)
        return getMockRadioStations().filter {
            it.name.contains(query, ignoreCase = true) ||
            it.genre?.contains(query, ignoreCase = true) == true ||
            it.city?.contains(query, ignoreCase = true) == true
        }
    }

    private fun getMockRadioStations(): List<RadioStation> {
        return listOf(
            RadioStation(
                id = "1",
                name = "Rádio Globo Rio",
                streamUrl = "http://radioglobo.giga.host.br:23456/;",
                imageUrl = "https://via.placeholder.com/200x200.png?text=Globo+Rio",
                description = "A melhor programação de notícias e esportes",
                genre = "Notícias",
                city = "Rio de Janeiro",
                state = "RJ"
            ),
            RadioStation(
                id = "2",
                name = "Jovem Pan FM",
                streamUrl = "https://r12.ciclano.io:10019/stream",
                imageUrl = "https://via.placeholder.com/200x200.png?text=Jovem+Pan",
                description = "A rádio que toca música",
                genre = "Pop",
                city = "São Paulo",
                state = "SP"
            ),
            RadioStation(
                id = "3",
                name = "Antena 1 FM",
                streamUrl = "https://antenaone.crossradio.com.br/stream/1;",
                imageUrl = "https://via.placeholder.com/200x200.png?text=Antena+1",
                description = "Músicas dos anos 70, 80 e 90",
                genre = "Pop",
                city = "São Paulo",
                state = "SP",
                bitrate = 128
            ),
            RadioStation(
                id = "4",
                name = "89 FM - A Rádio Rock",
                streamUrl = "https://stream.89fm.com.br/live.aac",
                imageUrl = "https://via.placeholder.com/200x200.png?text=89+FM",
                description = "A melhor do rock",
                genre = "Rock",
                city = "São Paulo",
                state = "SP",
                bitrate = 128
            ),
            RadioStation(
                id = "5",
                name = "Transamérica Pop",
                streamUrl = "https://playerservices.streamtheworld.com/api/livestream-redirect/RT_AACP.aac",
                imageUrl = "https://via.placeholder.com/200x200.png?text=Transamérica",
                description = "Sempre com você",
                genre = "Pop",
                city = "São Paulo",
                state = "SP"
            ),
            RadioStation(
                id = "6",
                name = "Metropolitana FM",
                streamUrl = "https://stream.radio.co/s8b5f3e37e/listen",
                imageUrl = "https://via.placeholder.com/200x200.png?text=Metropolitana",
                description = "Sertanejo de qualidade",
                genre = "Sertanejo",
                city = "São Paulo",
                state = "SP"
            ),
            RadioStation(
                id = "7",
                name = "Rádio Bandeirantes",
                streamUrl = "https://stream.radiorcs.com.br:9000/;",
                imageUrl = "https://via.placeholder.com/200x200.png?text=Bandeirantes",
                description = "Jornalismo e informação",
                genre = "Notícias",
                city = "São Paulo",
                state = "SP"
            ),
            RadioStation(
                id = "8",
                name = "Mix FM",
                streamUrl = "https://playerservices.streamtheworld.com/api/livestream-redirect/MIXFMSP.aac",
                imageUrl = "https://via.placeholder.com/200x200.png?text=Mix+FM",
                description = "A rádio que toca de tudo",
                genre = "Pop",
                city = "São Paulo",
                state = "SP"
            ),
            RadioStation(
                id = "9",
                name = "Rádio Cidade FM",
                streamUrl = "https://stream.radiocidade.fm/radiocidade.aac",
                imageUrl = "https://via.placeholder.com/200x200.png?text=Cidade+FM",
                description = "O melhor do pop rock nacional e internacional",
                genre = "Rock",
                city = "Rio de Janeiro",
                state = "RJ"
            ),
            RadioStation(
                id = "10",
                name = "CBN São Paulo",
                streamUrl = "https://medias.sgr.globo.com/hls/vCBNSP/vCBNSP.m3u8",
                imageUrl = "https://via.placeholder.com/200x200.png?text=CBN",
                description = "A rádio que toca notícia",
                genre = "Notícias",
                city = "São Paulo",
                state = "SP"
            )
        )
    }
}
