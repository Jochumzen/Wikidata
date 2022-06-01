package com.mapifesto.wikidata_datasource

import com.mapifesto.domain.Wikipedia
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

interface WikipediaIntermediary {
    fun getWikipediaSiteByWikiId(wikiId: String, callback: (DataState<Wikipedia>) -> Unit)
}

class WikipediaIntermediaryImpl: WikipediaIntermediary {
    override fun getWikipediaSiteByWikiId(wikiId: String, callback: (DataState<Wikipedia>) -> Unit) {

        val getWikipediaSites = WikidataInteractors.build().getWikipediaSites
        getWikipediaSites.execute(wikidataId = wikiId).onEach { dataState ->

            callback(dataState)
        }.launchIn(CoroutineScope(Dispatchers.Main))


    }


}

class WikipediaIntermediaryMockup: WikipediaIntermediary {
    override fun getWikipediaSiteByWikiId(wikiId: String, callback: (DataState<Wikipedia>) -> Unit) {
        TODO("Not yet implemented")
    }

}