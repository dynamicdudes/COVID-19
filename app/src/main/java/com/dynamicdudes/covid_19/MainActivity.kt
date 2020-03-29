package com.dynamicdudes.covid_19

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.dynamicdudes.covid_19.callbacks.ApiService
import com.dynamicdudes.covid_19.data.Covid
import com.dynamicdudes.covid_19.fragments.FragmentAboutus
import com.dynamicdudes.covid_19.fragments.FragmentOther
import com.dynamicdudes.covid_19.fragments.IndiaFragement
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val indiaFragment = IndiaFragement()
        setCurrentfragment(indiaFragment)
        initApiCalls()
        bottom_nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home_menu -> {
                    setCurrentfragment(indiaFragment)
                }
                R.id.check -> {
                    val fragmentOther = FragmentOther()
                    setCurrentfragment(fragmentOther)
                }
                R.id.about -> {
                    val fragmentAboutus = FragmentAboutus()
                    setCurrentfragment(fragmentAboutus)
                }
            }
            true
        }
    }

    fun initApiCalls(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://corona.lmao.ninja")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(ApiService::class.java)
        api.fetchAllDetails().enqueue(object : Callback<Covid>{
            override fun onFailure(call: Call<Covid>, t: Throwable) {
                println("Failed to Fetch...")
                d(TAG,"${t.message} because of ${t.cause}")
            }
            override fun onResponse(call: Call<Covid>, response: Response<Covid>) {
                println("Fetched Successfully....Cases: ${response.body()!!.cases}")
            }
        })
    }

    fun setCurrentfragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout_main,fragment)
            commit()
        }
    }
}
