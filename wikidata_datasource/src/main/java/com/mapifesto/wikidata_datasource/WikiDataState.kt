package com.mapifesto.wikidata_datasource

sealed class WikiDataState<T> {

    data class Error<T>(
        val error: String
    ): WikiDataState<T>()

    data class WikiData<T>(
        val data: T
    ): WikiDataState<T>()

}