package com.mapifesto.wikidata_datasource

sealed class DataState<T> {

    data class Error<T>(
        val error: String
    ): DataState<T>()

    data class Data<T>(
        val data: T
    ): DataState<T>()

}