package com.netflix.clone.core

object Constants {
    const val TMDB_BASE_URL = "https://api.themoviedb.org/"
    private const val TMDB_IMAGES_BASE_URL = "https://image.tmdb.org/t/p/"
    const val TMDB_IMAGES_PREFIX_ORIGINAL = "${TMDB_IMAGES_BASE_URL}original"
    const val TMDB_BACKDROP_PREFIX = "${TMDB_IMAGES_BASE_URL}w780"
    const val TMDB_LOGO_PREFIX = "${TMDB_IMAGES_BASE_URL}w300"
    const val TMDB_POSTER_PREFIX = "${TMDB_IMAGES_BASE_URL}w500"
    const val TMDB_PROFILE_PREFIX = "${TMDB_IMAGES_BASE_URL}w185"
    const val TMDB_STILL_PREFIX = "${TMDB_IMAGES_BASE_URL}w185"
}
