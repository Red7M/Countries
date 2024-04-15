package com.example.listofcountries.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Currency(
    @SerializedName("code")
    val currencyCode: String,
    @SerializedName("name")
    val currencyName: String,
    @SerializedName("symbol")
    val currencySymbol: String
)