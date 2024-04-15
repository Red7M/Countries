package com.example.listofcountries.domain.repository

import com.example.listofcountries.data.remote.dto.CountryDto

interface CountriesRepository {
    suspend fun getCountries() : List<CountryDto>
}