package com.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.books.book
import com.example.myapplication.databinding.SeekingItemBinding

class adapterToSearch(val list:List<book>, val itemclick: adapter.OnClick) : RecyclerView.Adapter<adapterToSearch.SearchHolder>() {
    class SearchHolder(binding:SeekingItemBinding): RecyclerView.ViewHolder(binding.root){
        val image = binding.imageView6
        val name = binding.textView20
        val card = binding.searchcard
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        return SearchHolder(SeekingItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        holder.image.setBackgroundResource(list[position].photo)
        holder.name.text = list[position].name
        holder.card.setOnClickListener {
            itemclick.click(list[position])
        }
    }
}