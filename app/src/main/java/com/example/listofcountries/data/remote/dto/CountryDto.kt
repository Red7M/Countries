package com.example.listofcountries.data.remote.dto

import com.example.listofcountries.domain.model.Country

data class CountryDto(
    val capital: String,
    val code: String,
    val currency: Currency,
    val flag: String,
    val language: Language,
    val name: String,
    val region: String
)

/**
 * Maps CountryDto to Country simplified object that only contains required and necessary properties
 */
fun CountryDto.toCountry() : Country {
    return Country(
        name = name,
        region = region,
        code = code,
        capital = capital)
}