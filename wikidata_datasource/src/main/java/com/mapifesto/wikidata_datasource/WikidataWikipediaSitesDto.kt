package com.mapifesto.datasource_wikidata

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WikidataWikipediaSitesDto(

    @SerialName("entities")
    val entities: Map<String,WikidataEntityDto>,

    @SerialName("success")
    val success: Int
) {

    @Serializable
    data class WikidataEntityDto(

        @SerialName("type")
        val type: String,

        @SerialName("id")
        val id: String,

        @SerialName("sitelinks")
        val siteLinks: Map<String, WikiLangDto>,
    )

    @Serializable
    data class WikiLangDto (

        @SerialName("site")
        val site: String,

        @SerialName("title")
        val title: String,

        @SerialName("badges")
        val badges: List<String>,
    )

}

