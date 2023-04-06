package com.example.purpleplay_android.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.purpleplay_android.R

class TermsAndConditionFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_terms_and_condition, container, false)
        val backView = view.findViewById<LinearLayout>(R.id.backView)
        backView.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()
        }
        return view
    }
}