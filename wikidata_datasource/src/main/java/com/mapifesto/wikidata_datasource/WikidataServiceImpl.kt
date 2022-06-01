package com.mapifesto.wikidata_datasource

import com.mapifesto.datasource_wikidata.WikidataWikipediaSitesDto
import com.mapifesto.wikidata_datasource.EndPoints.MAIN
import io.ktor.client.*
import io.ktor.client.request.*

class WikidataServiceImpl(
    private val httpClient: HttpClient,
): WikidataService {

    override suspend fun getWikipediaSites(wikidataId: String): WikidataWikipediaSitesDto {
        return httpClient.get {
            url("$MAIN?action=wbgetentities&format=json&props=sitelinks&ids=$wikidataId")
            //url("https://www.wikidata.org/w/api.php?action=wbgetentities&format=json&props=sitelinks&ids=Q1236689")
        }

    }

}