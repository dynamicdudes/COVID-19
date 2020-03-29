package com.dynamicdudes.covid_19.fragments

import androidx.fragment.app.Fragment
import com.dynamicdudes.covid_19.R

class DetailsFragment : Fragment(R.layout.fragment_details)

interface FragmentCommunication {
    fun respond(position: Int, name: String?, job: String?)
}