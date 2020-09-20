package com.example.appyhightask.Activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.appyhightask.R
import com.example.appyhightask.Repo.NewsRepository
import com.example.appyhightask.db.ArticleDatabase
import com.example.appyhightask.fragments.SearchNews
import com.example.appyhightask.fragments.shownews
import com.example.appyhightask.ui.NewsViewModelProvider
import com.example.appyhightask.vm.NewsViewModel


import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class NewsActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener=BottomNavigationView.OnNavigationItemSelectedListener { item->
        when(item.itemId){
            R.id.action_home -> {
                movetoFragment(shownews())
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_search -> {
                movetoFragment(SearchNews())
                return@OnNavigationItemSelectedListener true
            }
            else->{
                    false
            }
        }
    }
    //private lateinit var NavControl:NavController
     lateinit var vmodel:NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //movetoFragment(shownews())
       // NavControl=Navigation.findNavController(
           // this,R.id.nav_host)
       /* mAdView = findViewById(R.id.adView)
        val adRequest: AdRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)*/
        val newsRepository=NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory=NewsViewModelProvider(newsRepository)
        vmodel=ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)
        bottomNavigationView.setupWithNavController(nav_host.findNavController())

    }
    private fun movetoFragment(name: Fragment){
        val fragmentTrans=supportFragmentManager.beginTransaction()
        fragmentTrans.replace(R.id.FragmentHolder, name)
        fragmentTrans.commit()
    }
}