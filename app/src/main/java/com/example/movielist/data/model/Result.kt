package com.example.movielist.data.model

data class Result(
    val byline: String,
    val critics_pick: Int,
    val date_updated: String,
    val display_title: String,
    val headline: String,
    val mpaa_rating: String,
    val multimedia: Multimedia,
    val opening_date: String,
    val publication_date: String,
    val summary_short: String
)


data class Multimedia(
    val height: Int,
    val src: String,
    val type: String,
    val width: Int
)