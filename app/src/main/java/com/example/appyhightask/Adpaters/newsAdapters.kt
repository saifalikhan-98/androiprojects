package com.example.appyhightask.Adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appyhightask.R
import com.example.appyhightask.models.Article
import kotlinx.android.synthetic.main.feed.view.*

class newsAdapters() :RecyclerView.Adapter<newsAdapters.ViewHolder>() {
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val image=itemView.findViewById<ImageView>(R.id.newsPhoto)
        val newsheadline=itemView.findViewById<TextView>(R.id.headline)
        val Description=itemView.findViewById<TextView>(R.id.news)

    }

    private val differCallBack=object:DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem==newItem
        }


    }
    val differ=AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val inflate=LayoutInflater.from(parent.context)
        val view=inflate.inflate(R.layout.feed,parent,false)


        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article=differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this)
                .load(article.urlToImage)
                .into(newsPhoto)

            headline.text=article.title
            news.text=article.description

            setOnClickListener {
                onItemClickListener?.let{ it(article) }
            }
        }
    }

    private var onItemClickListener : ((Article)-> Unit)?=null

    fun setOnItemClick(listener:(Article)->Unit){
       onItemClickListener=listener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
