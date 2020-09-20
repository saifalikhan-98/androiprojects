package com.example.appyhightask.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.appyhightask.Activities.NewsActivity
import com.example.appyhightask.R
import com.example.appyhightask.vm.NewsViewModel


class SearchNews : Fragment() {


    lateinit var vmodel: NewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        vmodel=(activity as NewsActivity).vmodel
       val view= inflater.inflate(R.layout.fragment_search_news, container, false)

        Toast.makeText(requireContext(),"THis is search fragment",Toast.LENGTH_LONG).show()
        return view
    }


}