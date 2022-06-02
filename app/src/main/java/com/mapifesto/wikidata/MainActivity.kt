package com.mapifesto.wikidata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mapifesto.domain.Wikipedia
import com.mapifesto.wikidata.ui.theme.WikidataTheme
import com.mapifesto.wikidata_datasource.WikiDataState
import com.mapifesto.wikidata_datasource.WikipediaIntermediary
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var wikipediaIntermediary: WikipediaIntermediary

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WikidataTheme {
                Compose(wikipediaIntermediary = wikipediaIntermediary)
            }
        }
    }
}

@Composable
fun Compose(
    wikipediaIntermediary: WikipediaIntermediary
) {

    var showWhat by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    var wikipedia by remember { mutableStateOf<Wikipedia?>(null)}

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column() {

            Row() {

                Button(
                    onClick = {
                        showWhat = "spinner"
                        error = ""
                        wikipediaIntermediary.getWikipediaSiteByWikiId("Q1236689") {
                            when(it) {
                                is WikiDataState.Error ->{
                                    error = it.error
                                }
                                is WikiDataState.WikiData -> {
                                    wikipedia = it.data
                                    showWhat = "wikipedia"
                                }
                            }
                        }

                    }
                ) {
                    Text("Get wikipedia")
                }
            }

            if(error != "") Text("Error: $error") else {
                when(showWhat) {
                    "wikipedia" -> {
                        Text("Url: ${wikipedia!!.url}")
                    }
                    "spinner" -> {
                        CircularProgressIndicator(
                            modifier = Modifier.padding(top = 10.dp)
                        )
                    }
                    else -> {}
                }
            }
        }

    }
}

