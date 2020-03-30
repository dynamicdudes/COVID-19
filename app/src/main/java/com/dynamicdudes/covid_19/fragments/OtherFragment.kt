package com.dynamicdudes.covid_19.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dynamicdudes.covid_19.R
import com.dynamicdudes.covid_19.adapters.CountriesRecyclerAdapter
import com.dynamicdudes.covid_19.callbacks.ApiService
import com.dynamicdudes.covid_19.callbacks.FragmentCommunication
import com.dynamicdudes.covid_19.data.all_countries
import kotlinx.android.synthetic.main.fragment_other.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class OtherFragment : Fragment(R.layout.fragment_other){
    val TAG = "OtherFragment"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countries_rc_view.visibility = View.INVISIBLE
        progressBar_details.visibility = View.VISIBLE
        FetchCountries()
    }

    private fun FetchCountries() {
        //Creating a retrofit Builder
        val retrofit = Retrofit.Builder()
            .baseUrl("https://corona.lmao.ninja")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //create API for countries fetch by no of cases
        val countriesAPI = retrofit.create(ApiService::class.java)

        //implementing the method to fetch all country
        countriesAPI.fetchAllCountries().enqueue(object :Callback<all_countries>{
            override fun onFailure(call: Call<all_countries>, t: Throwable) {
                Log.e(TAG,"Failed to Perform as ${t.cause} and ${t.message}")
                AlertDialog.Builder(activity!!)
                    .setTitle("Failed to Fetch")
                    .setTitle("Unable to fetch the Data , check if there is active internet connection. if the problem still persist contact us")
                    .setIcon(R.drawable.ic_error_outline_black_24dp)
                    .setNegativeButton("Okay", DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                    })
                    .show()
            }
            val communication = object : FragmentCommunication {
                override fun respond(countryname: String) {
                    val DetailsFragment = DetailsFragment()
                    val bundle = Bundle()
                    bundle.putString("country",countryname)
                    DetailsFragment.arguments = bundle
                    fragmentManager!!.beginTransaction().apply {
                        replace(R.id.frame_layout_main,DetailsFragment)
                        commit()
                    }
                }
            }
            override fun onResponse(call: Call<all_countries>, response: Response<all_countries>) {
                val countryRecyclerAdapter = CountriesRecyclerAdapter(response.body()!!,communication)
                countries_rc_view.adapter = countryRecyclerAdapter
                countries_rc_view.layoutManager = LinearLayoutManager(activity!!)
                progressBar_details.visibility = View.INVISIBLE
                countries_rc_view.visibility = View.VISIBLE
            }

        })
    }
}