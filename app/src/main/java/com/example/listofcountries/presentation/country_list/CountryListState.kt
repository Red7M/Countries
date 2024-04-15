package com.example.listofcountries.presentation.country_list

import com.example.listofcountries.domain.model.Country

data class CountryListState(
    val isLoading: Boolean = false,
    val countries : List<Country> = emptyList(),
    val error : String = ""
)
