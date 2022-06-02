package com.mapifesto.wikidata_datasource

import com.mapifesto.domain.Wikipedia
import com.mapifesto.domain.WikipediaType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

interface WikipediaIntermediary {
    fun getWikipediaSiteByWikiId(wikiId: String, callback: (WikiDataState<Wikipedia>) -> Unit)
}

class WikipediaIntermediaryImpl: WikipediaIntermediary {
    override fun getWikipediaSiteByWikiId(wikiId: String, callback: (WikiDataState<Wikipedia>) -> Unit) {

        val getWikipediaSites = WikidataInteractors.build().getWikipediaSites
        getWikipediaSites.execute(wikidataId = wikiId).onEach { dataState ->

            callback(dataState)
        }.launchIn(CoroutineScope(Dispatchers.Main))


    }


}

class WikipediaIntermediaryMockup: WikipediaIntermediary {
    override fun getWikipediaSiteByWikiId(wikiId: String, callback: (WikiDataState<Wikipedia>) -> Unit) {
        callback(WikiDataState.WikiData(Wikipedia(url = "https://en.wikipedia.org/wiki/Eric_Cartman", type = WikipediaType.EN)))
    }

}