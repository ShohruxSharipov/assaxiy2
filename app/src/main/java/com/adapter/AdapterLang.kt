package com.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.books.Language
import com.example.myapplication.databinding.LanguageBinding

class AdapterLang(val list: MutableList<Language>,val clck : clickLan): RecyclerView.Adapter<AdapterLang.LanHolder>() {
    class LanHolder(binding: LanguageBinding) : RecyclerView.ViewHolder(binding.root){
        val flag = binding.imageView8
        val lanTitle = binding.language
        val switch = binding.wholeLanFrame
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanHolder {
        return LanHolder(LanguageBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: LanHolder, position: Int) {
        holder.flag.setBackgroundResource(list[position].flag)
        holder.lanTitle.text = list[position].name
        holder.switch.setOnClickListener{
            clck.SwichLanguage(list[position])
        }
    }

    interface clickLan{
        fun SwichLanguage(language: Language)
    }
}