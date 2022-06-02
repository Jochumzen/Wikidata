package com.mapifesto.wikidata_datasource

import com.mapifesto.datasource_wikidata.WikidataWikipediaSitesDto
import com.mapifesto.domain.Wikipedia
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWikipediaSites(
    private val service: WikidataService,
) {

    fun execute(
        wikidataId: String
    ): Flow<WikiDataState<Wikipedia>> = flow {


        var errorMessage: String? = null

        val wikipediaSites: WikidataWikipediaSitesDto? = try {
            service.getWikipediaSites(wikidataId = wikidataId)
        } catch (e: Exception) {
            e.printStackTrace()
            errorMessage = e.message?: "No error message provided"
            null
        }

        if(wikipediaSites == null) {
            emit(WikiDataState.Error("Error executing GetWikipediaSites. Error message: $errorMessage"))
            return@flow
        }

        val wikipedia = Mapper.createWikipedia(wikipediaSites, wikidataId)

        emit(wikipedia)

    }


}

