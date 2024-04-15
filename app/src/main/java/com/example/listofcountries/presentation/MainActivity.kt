package com.example.listofcountries.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listofcountries.databinding.ActivityMainBinding
import com.example.listofcountries.presentation.country_list.CountriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var countryListAdapter : CountriesAdapter? = null

    private val countriesViewModel: CountriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setUpViews()
        setConsumers()
    }

    /**
     * Sets views of the main activity screen
     */
    private fun setUpViews() {
        binding.countriesRecyclerView.layoutManager = LinearLayoutManager(this)
        countryListAdapter = CountriesAdapter()
        binding.countriesRecyclerView.adapter = countryListAdapter
    }

    /**
     * Sets collector for state of the countries. Possible results are: list of country, loading, or error state
     */
    private fun setConsumers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                countriesViewModel.countriesState.collect { countriesState ->
                    if (countriesState.isLoading) {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.countriesRecyclerView.visibility = View.INVISIBLE
                        binding.errorText.visibility = View.INVISIBLE
                        return@collect
                    } else if (countriesState.countries.isNotEmpty()){
                        binding.progressBar.visibility = View.INVISIBLE
                        binding.countriesRecyclerView.visibility = View.VISIBLE
                        binding.errorText.visibility = View.INVISIBLE

                        // submit data to the adapter
                        countryListAdapter!!.submitList(countriesState.countries)
                    } else {
                        binding.progressBar.visibility = View.INVISIBLE
                        binding.countriesRecyclerView.visibility = View.INVISIBLE
                        binding.errorText.visibility = View.VISIBLE

                        // Set error text
                        binding.errorText.text = countriesState.error
                    }
                }
            }
        }
    }
}