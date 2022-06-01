package com.mapifesto.wikidata_datasource

import com.mapifesto.datasource_wikidata.WikidataWikipediaSitesDto
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*

interface WikidataService {

    suspend fun getWikipediaSites(wikidataId: String): WikidataWikipediaSitesDto

    companion object Factory {
        fun build(): WikidataService {
            return WikidataServiceImpl(
                httpClient = HttpClient(Android) {
                    install(JsonFeature) {
                        serializer = KotlinxSerializer(
                            kotlinx.serialization.json.Json {
                                ignoreUnknownKeys = false
                                isLenient = false
                            }
                        )
                    }
                    //install(HttpTimeout)
                }
            )
        }
    }


}