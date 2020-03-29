package com.dynamicdudes.covid_19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.dynamicdudes.covid_19.fragments.AboutUsFragment
import com.dynamicdudes.covid_19.fragments.OtherFragment
import com.dynamicdudes.covid_19.fragments.IndiaFragement
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val indiaFragment = IndiaFragement()
        setCurrentfragment(indiaFragment)
        bottom_nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home_menu -> {
                    setCurrentfragment(indiaFragment)
                }
                R.id.check -> {
                    val fragmentOther = OtherFragment()
                    setCurrentfragment(fragmentOther)
                }
                R.id.about -> {
                    val fragmentAboutus = AboutUsFragment()
                    setCurrentfragment(fragmentAboutus)
                }
            }
            true
        }
    }

    fun setCurrentfragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout_main,fragment)
            commit()
        }
    }
}
