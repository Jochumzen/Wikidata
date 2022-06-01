package com.mapifesto.wikidata_datasource

object EndPoints {

    private const val OVERPASS_URL = "https://www.wikidata.org"

    private const val MAIN_API = "/w/api.php"

    const val MAIN = "$OVERPASS_URL$MAIN_API"
}