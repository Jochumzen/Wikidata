package com.mapifesto.wikidata

import com.mapifesto.wikidata_datasource.WikipediaIntermediary
import com.mapifesto.wikidata_datasource.WikipediaIntermediaryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    @Singleton
    @Provides
    fun provideOverpassIntermediary(

    ) : WikipediaIntermediary {
        return WikipediaIntermediaryImpl()
    }

}