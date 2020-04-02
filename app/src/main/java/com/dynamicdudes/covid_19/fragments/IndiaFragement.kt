package com.dynamicdudes.covid_19.fragments


import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.dynamicdudes.covid_19.R
import com.dynamicdudes.covid_19.callbacks.ApiService
import com.dynamicdudes.covid_19.data.Coviddata
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class IndiaFragement : Fragment(R.layout.fragement_india){
    val TAG = "IndiaFragment"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressbar = view.findViewById<ProgressBar>(R.id.progress_def)
        val scrollView = view.findViewById<ScrollView>(R.id.scrollview_def)
        val image = view.findViewById<ImageView>(R.id.country_flag_def)
        val countryName = view.findViewById<TextView>(R.id.country_name_def)
        val updateDate = view.findViewById<TextView>(R.id.update_date_def)
        //make views invisible
        countryName.visibility = View.INVISIBLE
        image.visibility = View.INVISIBLE
        updateDate.visibility = View.INVISIBLE
        scrollView.visibility = View.INVISIBLE
        progressbar.visibility = View.VISIBLE
        initApiCalls(view)
    }

    fun initApiCalls(view: View) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://corona.lmao.ninja")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(ApiService::class.java)
        api.fetchAllDetails("india").enqueue(object : Callback<Coviddata> {
            override fun onFailure(call: Call<Coviddata>, t: Throwable) {
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

            override fun onResponse(call: Call<Coviddata>, response: Response<Coviddata>) {
                if(response.isSuccessful){
                    d(TAG, "Fetched Successfully....Cases: ${response.body()!!.cases}")
                    val flagURL = response.body()!!.countryInfo.flag
                    val responseJson = response.body()!!
                    d(TAG,"Flag url $flagURL")
                    val image = view.findViewById<ImageView>(R.id.country_flag_def)
                    val countryName = view.findViewById<TextView>(R.id.country_name_def)
                    val updateDate = view.findViewById<TextView>(R.id.update_date_def)
                    val totalCases = view.findViewById<TextView>(R.id.total_cases_def)
                    val todayCases = view.findViewById<TextView>(R.id.today_cases_def)
                    val totalDeath = view.findViewById<TextView>(R.id.total_death_def)
                    val todayDeath = view.findViewById<TextView>(R.id.today_death_def)
                    val totalRecovered = view.findViewById<TextView>(R.id.total_recovered_def)
                    val activeCases = view.findViewById<TextView>(R.id.active_def)
                    val criticalCases = view.findViewById<TextView>(R.id.critical_def)
                    val caseMillion = view.findViewById<TextView>(R.id.total_casesp_m_def)
                    val deathMillion = view.findViewById<TextView>(R.id.total_deathp_m_def)
                    val progressbar = view.findViewById<ProgressBar>(R.id.progress_def)
                    val scrollView = view.findViewById<ScrollView>(R.id.scrollview_def)

                    countryName.text = responseJson.country
                    Picasso.get().load(flagURL).into(image)
                    /*
                    *  dd / MM / yyyy HH:mm:ss
                    *
                    * */

                    val time = Calendar.getInstance()
                    time.add(Calendar.MILLISECOND,time.timeZone.getOffset(responseJson.updated))
                    val udate = time.time
                    val stringTime = udate.toString().subSequence(0,udate.toString().indexOf(" GMT"))
                    updateDate.text = "Last updated\n$stringTime"
                    totalCases.text = "Total cases Count : ${responseJson.cases}"
                    todayCases.text = "Today's cases Count : ${responseJson.todayCases}"
                    totalDeath.text = "Total Death Count : ${responseJson.deaths}"
                    todayDeath.text = "Today Death Count : ${responseJson.todayDeaths}"
                    totalRecovered.text = "Total Recovered Cases : ${responseJson.recovered}"
                    activeCases.text = "Still Active cases : ${responseJson.active}"
                    criticalCases.text = "Total Critical cases :${responseJson.critical}"
                    caseMillion.text = "Cases Per One Million : ${responseJson.casesPerOneMillion}"
                    deathMillion.text = "Death Per One Million : ${responseJson.deathsPerOneMillion}"
                    // making views visible
                    progressbar.visibility = View.INVISIBLE
                    countryName.visibility = View.VISIBLE
                    image.visibility = View.VISIBLE
                    updateDate.visibility = View.VISIBLE
                    scrollView.visibility = View.VISIBLE
                }
            }
        })
    }
}