package com.example.listofcountries.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.listofcountries.databinding.CountryCardItemBinding
import com.example.listofcountries.domain.model.Country

/**
 * Adapters provide a binding from an countries data to views that are displayed within the RecyclerView.
 */
class CountriesAdapter : RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = CountryCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.setData(differ.currentList[holder.adapterPosition])
    }

    override fun getItemCount(): Int = differ.currentList.size

    /**
     * submits list of countries to the adapter helper.
     */
    fun submitList(countries: List<Country>) {
        differ.submitList(countries)
    }

    /**
     * Class responsible for holding single item country meta-data
     */
    inner class CountryViewHolder(private val binding: CountryCardItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setData(country: Country) {
            binding.apply {
                name.text = country.name
                region.text = country.region
                code.text = country.code
                capital.text = country.capital
            }
        }

    }

    /**
     * Callback for calculating differences between two non-null countries in the list.
     */
    private val differCallback = object : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.code == newItem.code
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)
}