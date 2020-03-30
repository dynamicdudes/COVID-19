package com.dynamicdudes.covid_19

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.dynamicdudes.covid_19.fragments.OtherFragment
import com.dynamicdudes.covid_19.fragments.IndiaFragement
import com.dynamicdudes.covid_19.fragments.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
private var isFragmentAttached = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val preference = getSharedPreferences("DarkMode", Context.MODE_PRIVATE)
        val isdark = preference.getBoolean("isDarkMode",false)
        if (isdark){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            initalizeView()
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            initalizeView()
        }
    }

    fun initalizeView(){
        bottom_nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home_menu -> {
                    val fragment = IndiaFragement()
                    setCurrentfragment(fragment)
                }
                R.id.check -> {
                    val fragmentOther = OtherFragment()
                    setCurrentfragment(fragmentOther)
                }
                R.id.about -> {
                    val settingsFragment = SettingsFragment()
                    setCurrentfragment(settingsFragment)
                }
                else -> {
                    val fragment = IndiaFragement()
                    setCurrentfragment(fragment)
                }
            }
            true
        }
        if (!isFragmentAttached){
            bottom_nav.selectedItemId = R.id.home_menu
        }
    }
    fun setCurrentfragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout_main,fragment)
            commit()
        }
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        isFragmentAttached = true
    }
}
