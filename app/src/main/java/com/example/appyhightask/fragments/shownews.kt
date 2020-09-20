package com.example.appyhightask.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.appyhightask.Activities.NewsActivity
import com.example.appyhightask.Adpaters.newsAdapters
import com.example.appyhightask.R
import com.example.appyhightask.util.Resource
import com.example.appyhightask.vm.NewsViewModel
import kotlinx.android.synthetic.main.fragment_shownews.*
import java.util.Observer


class shownews : Fragment() {
    private lateinit var recycler: RecyclerView
    lateinit var vmodel: NewsViewModel
    lateinit var nadapter: newsAdapters
    lateinit var ind:Button
    lateinit var us:Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_shownews, container, false)
        recycler=view.findViewById(R.id.newsActual)
        ind=view.findViewById(R.id.India)
        us=view.findViewById(R.id.USA)

        recycler.setHasFixedSize(true)
        vmodel=(activity as NewsActivity).vmodel
      ind.setOnClickListener {
            vmodel.getBreakingNews("in")
        }
       us.setOnClickListener {
            vmodel.getBreakingNews("us")
        }



        vmodel.breakingnews.observe(viewLifecycleOwner, androidx.lifecycle.Observer { response->
            when(response){
                is Resource.Success ->{
                    response.data?.let {newsresponse->
                        nadapter.differ.submitList(newsresponse.articles)
                    }
                }
                is Resource.Error->{
                    response.message?.let{
                        Log.d("error","An Error Occurred ${it}")

                    }
                }
                is Resource.Loading->{
                    Log.d("error","Loading ")

                }

            }
        })

        setUpRecyclerView()
        nadapter.setOnItemClick {
            val bundle=Bundle().apply{
                putSerializable("Article",it)

            }
            findNavController().navigate(
                R.id.action_shownews2_to_newsDetail,
                bundle
            )
        }
        return view

    }

    private fun setUpRecyclerView() {
        nadapter=newsAdapters()
        recycler.apply {

            adapter=nadapter
        }

    }


}
