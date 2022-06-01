package com.mapifesto.wikidata_datasource

import com.mapifesto.datasource_wikidata.WikidataWikipediaSitesDto
import com.mapifesto.domain.Wikipedia
import com.mapifesto.domain.WikipediaType

object Mapper {

    fun createWikipedia(sites: WikidataWikipediaSitesDto, wikidataId: String): DataState<Wikipedia> {

        if(sites.success != 1) {
            return DataState.Error("Error executing GetWikipediaSites. Success is not equal to 1.")
        }

        val entities = sites.entities

        if(entities.size != 1) {
            return DataState.Error("Error executing GetWikipediaSites. Size of entities is not 1.")
        }

        val wikidataEntity = entities[wikidataId]
            ?: return DataState.Error("Error executing GetWikipediaSites. Entities dos not contain a wikidataEntity with given wikidataId")

        val siteLinks = wikidataEntity.siteLinks

        val enwiki = siteLinks["enwiki"]

        return if(enwiki != null) DataState.Data(Wikipedia(url = enwiki.title, type = WikipediaType.EN)) else {

            val svwiki = siteLinks["svwiki"]

            if(svwiki != null)
                DataState.Data(Wikipedia(url = svwiki.title, type = WikipediaType.SV)) else
                DataState.Data(Wikipedia(url = "", type = WikipediaType.NONE))
        }

    }
}