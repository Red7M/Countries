package com.example.listofcountries.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Language(
    @SerializedName("code")
    val languageCode: String,
    @SerializedName("name")
    val languageName: String
)