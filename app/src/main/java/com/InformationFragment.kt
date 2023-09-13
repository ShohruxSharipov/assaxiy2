package com

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.books.book
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentInformationBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class InformationFragment : Fragment() {
    private var param1: String? = null
    private var param2: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentInformationBinding.inflate(inflater,container,false)
        val gson = Gson()
        val type = object : TypeToken<List<book>>() {}.type
        val activity = activity as AppCompatActivity
        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        val list: List<book>
        val str = cache.getString("books","")
        list = gson.fromJson(str,type)
        val i = param2!!


        binding.imageView4.setOnClickListener{
            val activity : AppCompatActivity = activity as AppCompatActivity
            activity.onBackPressedDispatcher.onBackPressed()
        }

        binding.imageView3.setBackgroundResource(list[i].photo)
        binding.textView13.text = list[i].name

        if (list[i].selected){
            binding.imageView5.setBackgroundResource(R.drawable.baseline_bookmark_24)
        }else {
            binding.imageView5.setBackgroundResource(R.drawable.baseline_bookmark)
        }

        binding.imageView5.setOnClickListener {
            if (!list[i].selected) {
                binding.imageView5.setBackgroundResource(R.drawable.baseline_bookmark_24)
                list[i].selected = true

            } else {
                binding.imageView5.setBackgroundResource(R.drawable.baseline_bookmark)
                list[i].selected = false
            }
            cache.edit().putString("books",gson.toJson(list)).apply()
            Toast.makeText(requireContext(), "${list[i].selected}", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: Int) =
            InformationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putInt(ARG_PARAM2, param2)
                }
            }
    }
}