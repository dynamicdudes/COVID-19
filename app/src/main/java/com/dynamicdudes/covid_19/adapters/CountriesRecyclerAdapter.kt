package com.dynamicdudes.covid_19.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dynamicdudes.covid_19.R
import com.dynamicdudes.covid_19.data.all_countries
import com.google.android.material.snackbar.Snackbar


class CountriesRecyclerAdapter(val listOfCountries : all_countries) : RecyclerView.Adapter<CountriesRecyclerAdapter.CountriesviewHolder>(){

    class CountriesviewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val countryBlock = itemView.findViewById<LinearLayout>(R.id.root_rc_item)
        val countryFlagImage = itemView.findViewById<ImageView>(R.id.flag_image_rc_item)
        val countryNameText = itemView.findViewById<TextView>(R.id.country_name_rc_item)
        val countryCasesText = itemView.findViewById<TextView>(R.id.country_cases_rc_item)
        val countryDeathText = itemView.findViewById<TextView>(R.id.country_death_rc_item)
    }
    interface OnRecyclerListner {
        fun onCountrySelected(country: String)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesviewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_item,parent,false)
        return CountriesviewHolder(view)
    }

    override fun getItemCount(): Int {
        return  listOfCountries.size
    }

    override fun onBindViewHolder(holder: CountriesviewHolder, position: Int) {
        holder.countryNameText.text = listOfCountries[position].country
        val flagUrl = listOfCountries[position].countryInfo.flag
        Glide.with(holder.itemView.context)
            .load(flagUrl)
            .into(holder.countryFlagImage)
        holder.countryCasesText.text = "Cases : ${listOfCountries[position].cases}"
        holder.countryDeathText.text = "Deaths : ${listOfCountries[position].deaths}"
        holder.countryBlock.setOnClickListener {
            Snackbar.make(holder.itemView,"you pressed ${listOfCountries[position].country} country",Snackbar.LENGTH_SHORT).show()
            holder.itemView
        }

    }

}