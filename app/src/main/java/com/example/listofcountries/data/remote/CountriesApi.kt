package com.example.listofcountries.data.remote

import com.example.listofcountries.data.remote.dto.CountryDto
import retrofit2.http.GET

interface CountriesApi {

    @GET(".")
    suspend fun getCountries() : List<CountryDto>
}