package com.example.listofcountries.data.repository

import com.example.listofcountries.data.remote.CountriesApi
import com.example.listofcountries.data.remote.dto.CountryDto
import com.example.listofcountries.domain.repository.CountriesRepository
import javax.inject.Inject

/**
 * Class responsible for requesting data from the "gist.githubusercontent.com" api
 */
class CountriesRepositoryImpl @Inject constructor(private val countriesApi: CountriesApi) : CountriesRepository {

    /**
     * Calls for a request to fetch list of countries from countries-api
     */
    override suspend fun getCountries(): List<CountryDto> {
        return countriesApi.getCountries()
    }
}