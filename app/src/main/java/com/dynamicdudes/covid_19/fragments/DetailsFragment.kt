package com.dynamicdudes.covid_19.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.dynamicdudes.covid_19.R
import com.dynamicdudes.covid_19.callbacks.ApiService
import com.dynamicdudes.covid_19.data.Coviddata
import kotlinx.android.synthetic.main.fragment_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*


class DetailsFragment : Fragment(R.layout.fragment_details){
    val TAG = javaClass.simpleName


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val country = arguments!!.getString("country")!!
        country_name_details.visibility = View.INVISIBLE
        country_flag_details.visibility = View.INVISIBLE
        update_date_details.visibility = View.INVISIBLE
        scrollview_details.visibility = View.INVISIBLE
        progress_details.visibility = View.VISIBLE
        FetchData(country)
    }

    private fun FetchData(country: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://corona.lmao.ninja")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val countryApi = retrofit.create(ApiService::class.java)
        countryApi.fetchAllDetails(country).enqueue(object : Callback<Coviddata>{
            override fun onFailure(call: Call<Coviddata>, t: Throwable) {
                Log.e(TAG,"Failed to Perform as ${t.cause} and ${t.message}")
                AlertDialog.Builder(activity!!)
                    .setTitle("Failed to Fetch")
                    .setTitle("Unable to fetch the Data , check if there is active internet connection. if the problem still persist contact us")
                    .setIcon(R.drawable.ic_error_outline_black_24dp)
                    .setNegativeButton("Okay", DialogInterface.OnClickListener { dialog, _ ->
                        dialog.dismiss()
                    })
                    .show()
            }

            override fun onResponse(call: Call<Coviddata>, response: Response<Coviddata>) {
                val responseJson = response.body()!!
                country_name_details.text = responseJson.country
                Glide.with(activity!!)
                    .load(responseJson.countryInfo.flag)
                    .into(country_flag_details)
                val time = Calendar.getInstance()
                time.add(Calendar.MILLISECOND,time.timeZone.getOffset(responseJson.updated))
                val update = time.time
                val stringTime = update.toString().subSequence(0,update.toString().indexOf(" GMT"))

//                val date = SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
//                    .format(Date(responseJson.updated * 1000))
                update_date_details.text = "Last updated\n$stringTime"
                total_cases_details.text = "Total cases Count : ${responseJson.cases}"
                today_cases_details.text = "Today's cases Count : ${responseJson.todayCases}"
                total_death_details.text = "Total Death Count : ${responseJson.deaths}"
                today_death_details.text = "Today Death Count : ${responseJson.todayDeaths}"
                total_recovered_details.text = "Total Recovered Cases : ${responseJson.recovered}"
                active_details.text = "Still Active cases : ${responseJson.active}"
                critical_details.text = "Total Critical cases :${responseJson.critical}"
                total_casesp_m_details.text = "Cases Per One Million : ${responseJson.casesPerOneMillion}"
                total_deathp_m_details.text = "Death Per One Million : ${responseJson.deathsPerOneMillion}"

                country_name_details.visibility = View.VISIBLE
                country_flag_details.visibility = View.VISIBLE
                update_date_details.visibility = View.VISIBLE
                scrollview_details.visibility = View.VISIBLE
                progress_details.visibility = View.INVISIBLE
            }

        })
    }
}