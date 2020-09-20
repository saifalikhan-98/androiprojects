package com.example.appyhightask.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.appyhightask.Activities.NewsActivity
import com.example.appyhightask.R
import com.example.appyhightask.vm.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news_detail.*


class NewsDetail : Fragment() {

    lateinit var vmodel: NewsViewModel
    lateinit var webView:WebView
    val args : NewsDetailArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_news_detail, container, false)
        vmodel=(activity as NewsActivity).vmodel
        webView=view.findViewById(R.id.webView)

        val article=args.article
        webView.apply {
            webChromeClient= WebChromeClient() as WebChromeClient?
            article?.url?.let { loadUrl(it) }
        }

        return view
    }


}