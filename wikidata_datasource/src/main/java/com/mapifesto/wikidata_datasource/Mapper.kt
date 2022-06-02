package com.mapifesto.wikidata_datasource

import com.mapifesto.datasource_wikidata.WikidataWikipediaSitesDto
import com.mapifesto.domain.Wikipedia
import com.mapifesto.domain.WikipediaType

object Mapper {

    fun createWikipedia(sites: WikidataWikipediaSitesDto, wikidataId: String): WikiDataState<Wikipedia> {

        if(sites.success != 1) {
            return WikiDataState.Error("Error executing GetWikipediaSites. Success is not equal to 1.")
        }

        val entities = sites.entities

        if(entities.size != 1) {
            return WikiDataState.Error("Error executing GetWikipediaSites. Size of entities is not 1.")
        }

        val wikidataEntity = entities[wikidataId]
            ?: return WikiDataState.Error("Error executing GetWikipediaSites. Entities dos not contain a wikidataEntity with given wikidataId")

        val siteLinks = wikidataEntity.siteLinks

        val enwiki = siteLinks["enwiki"]

        return if(enwiki != null) WikiDataState.WikiData(Wikipedia(url = enwiki.title.replace(" ", "_"), type = WikipediaType.EN)) else {

            val svwiki = siteLinks["svwiki"]

            if(svwiki != null)
                WikiDataState.WikiData(Wikipedia(url = svwiki.title.replace(" ", "_"), type = WikipediaType.SV)) else
                WikiDataState.WikiData(Wikipedia(url = "", type = WikipediaType.NONE))
        }

    }
}