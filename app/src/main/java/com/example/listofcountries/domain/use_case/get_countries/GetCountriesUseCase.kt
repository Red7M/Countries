package com.example.listofcountries.domain.use_case.get_countries

import com.example.listofcountries.common.Resource
import com.example.listofcountries.data.remote.dto.toCountry
import com.example.listofcountries.domain.model.Country
import com.example.listofcountries.domain.repository.CountriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * GetCountriesUseCase Responsible for requesting countries list and emits result.
 */
class GetCountriesUseCase @Inject constructor(
    private val countriesRepository: CountriesRepository) {

    operator fun invoke() : Flow<Resource<List<Country>>> = flow {
        try {
            emit(Resource.Loading(listOf()))
            val countries = countriesRepository.getCountries().map { it.toCountry() }
            emit(Resource.Success(countries))

        } catch(e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Exception for an unexpected, non-2xx HTTP response"))
        } catch(e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "an I/O exception of some sort has occurred"))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "an exception has occurred"))
        }
    }
}