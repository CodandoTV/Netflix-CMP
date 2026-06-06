package com.codandotv.streamplayerapp.feature.liststreams.list.domain.model

data class Stream(
    val id : String,
    val name : String,
    val description : String,
    val posterPathUrl: String,
)
data class ListStream(
    val categoryName: String,
    val streams: List<com.codandotv.streamplayerapp.feature.liststreams.list.domain.model.Stream>
)
