package com.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.books.book
import com.example.myapplication.databinding.BookBinding

class adapter(val list:List<book>, val itemclick:OnClick):RecyclerView.Adapter<adapter.MyHolder>() {
    class MyHolder(binding: BookBinding):RecyclerView.ViewHolder(binding.root){
        val photo = binding.book
        var name = binding.name
        var rank = binding.star
        var item = binding.wholeCardBook
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(BookBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.name.text = list[position].name
        holder.rank.text = list[position].star
        holder.photo.setBackgroundResource(list[position].photo)
        holder.item.setOnClickListener {
            itemclick.click(list[position])
        }

    }

    interface OnClick{
        fun click(book: book)
    }
}