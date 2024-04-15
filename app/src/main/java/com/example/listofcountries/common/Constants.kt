package com.example.listofcountries.common

object Constants {

    private const val BASE_URL = "https://gist.githubusercontent.com/"
    private const val SUB_DIRECTORY_PEYMANO_WMT = "peymano-wmt/32dcb892b06648910ddd40406e37fdab/"
    private const val SUB_DIRECTORY_RAW = "raw/db25946fd77c5873b0303b858e861ce724e0dcd0/"
    private const val END_FRAGMENT = "countries.json/"

    const val FULL_URL = BASE_URL + SUB_DIRECTORY_PEYMANO_WMT + SUB_DIRECTORY_RAW + END_FRAGMENT
}