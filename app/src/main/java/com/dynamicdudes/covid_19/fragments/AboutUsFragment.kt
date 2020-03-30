package com.dynamicdudes.covid_19.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dynamicdudes.covid_19.R
import me.jfenn.attribouter.Attribouter

class AboutUsFragment :  Fragment(R.layout.fragement_aboutus){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragment = Attribouter.from(activity!!).toFragment()
        fragmentManager!!.beginTransaction().apply {
            replace(R.id.frame_layout_main,fragment)
            commit()
        }
    }
}