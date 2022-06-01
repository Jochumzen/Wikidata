package com.mapifesto.wikidata_datasource



data class WikidataInteractors(
    val getWikipediaSites: GetWikipediaSites
) {
    companion object Factory {
        fun build(): WikidataInteractors{
            val service = WikidataService.build()
            return WikidataInteractors(
                getWikipediaSites = GetWikipediaSites(
                    service = service,
                ),
            )
        }

    }
}


