package com.dynamicdudes.covid_19.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dynamicdudes.covid_19.R
import kotlinx.android.synthetic.main.fragment_other.*

class OtherFragment : Fragment(R.layout.fragment_other){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar
    }
}