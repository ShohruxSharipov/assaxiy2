package com.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.books.book
import com.example.myapplication.databinding.PagerItemBinding

class PagerAdapter(val list:MutableList<book>): PagerAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view.equals(`object`)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = PagerItemBinding.inflate(LayoutInflater.from(container.context),container,false)
        binding.putin.setBackgroundResource(list[position].photo)
        binding.titli.text = list[position].name
        return binding.root
    }
}