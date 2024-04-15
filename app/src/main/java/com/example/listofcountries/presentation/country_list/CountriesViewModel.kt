package com.example.listofcountries.presentation.country_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listofcountries.common.Resource
import com.example.listofcountries.domain.use_case.get_countries.GetCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(val countriesUseCase: GetCountriesUseCase) : ViewModel() {

    private val _countriesState = MutableStateFlow(CountryListState())
    val countriesState = _countriesState.asStateFlow()

    init {
        getCountries()
    }

    /**
     * calls respective use case and maps result to the [CountryListState] data class
     */
    private fun getCountries() {
        countriesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _countriesState.value = CountryListState(countries = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _countriesState.value = CountryListState(error = result.message ?: "An unexpected error happened")
                }
                is Resource.Loading -> {
                    _countriesState.value = CountryListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}