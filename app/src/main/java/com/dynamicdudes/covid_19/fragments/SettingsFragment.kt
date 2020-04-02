package com.dynamicdudes.covid_19.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.dynamicdudes.covid_19.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_settings.*
import stream.customalert.CustomAlertDialogue
import kotlin.properties.Delegates
class SettingsFragment : Fragment(R.layout.fragment_settings){
    private var isDarkMode by Delegates.notNull<Boolean>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        about_us.setOnClickListener {

            val alert = CustomAlertDialogue.Builder(context)
                .setStyle(CustomAlertDialogue.Style.DIALOGUE)
                .setTitle("Developers")
                .setMessage("K. Magesh Babu\nR. Malini\nR. Vishweshwaran\n(Founder of Dynamic Dudes)")
                .setNegativeText("SUPPORT US")
                .setOnNegativeClicked { view, dialog -> dialog.dismiss() }
                .build()
            alert.show()
        }
        darkmodeToggleSwitch.textOff = "Light Mode"
        darkmodeToggleSwitch.textOn = "Dark Mode"
        val sharedPreferences = this.activity!!.getSharedPreferences("DarkMode",Context.MODE_PRIVATE)
        isDarkMode = sharedPreferences.getBoolean("isDarkMode",false)
        if (isDarkMode){
            darkmodeToggleSwitch.toggle()
        }
        darkmodeToggleSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                Snackbar.make(view,"Turning on Dark mode",Snackbar.LENGTH_SHORT).show()
                val preference = this.activity?.getSharedPreferences("DarkMode", Context.MODE_PRIVATE)
                val editior = preference?.edit()
                editior?.putBoolean("isDarkMode",true)
                editior?.apply()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                fragmentManager?.saveFragmentInstanceState(this)
            }else{
                Snackbar.make(view,"Turning off Dark mode",Snackbar.LENGTH_SHORT).show()
                val preference = this.activity?.getSharedPreferences("DarkMode", Context.MODE_PRIVATE)
                val editior = preference?.edit()
                editior?.putBoolean("isDarkMode",false)
                editior?.apply()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

}