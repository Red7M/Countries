package com.example.listofcountries.di

import com.example.listofcountries.common.Constants
import com.example.listofcountries.data.remote.CountriesApi
import com.example.listofcountries.data.repository.CountriesRepositoryImpl
import com.example.listofcountries.di.AppModule.provideCountriesRepository
import com.example.listofcountries.domain.repository.CountriesRepository
import com.example.listofcountries.domain.use_case.get_countries.GetCountriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCountriesApi() : CountriesApi {
        return Retrofit.Builder()
            .baseUrl(Constants.FULL_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountriesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCountriesRepository(countriesApi: CountriesApi) : CountriesRepository {
        return CountriesRepositoryImpl(countriesApi)
    }
}

@Module
@InstallIn(ViewModelComponent::class)
internal object ViewModelCountriesModule {
    @Provides
    @ViewModelScoped
    fun provideCountriesUseCase(countriesRepository: CountriesRepository) = GetCountriesUseCase(countriesRepository)
}