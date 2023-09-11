package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.books.book
import com.example.myapplication.databinding.FragmentKirishBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.user.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Kirish.newInstance] factory method to
 * create an instance of this fragment.
 */
class Kirish : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentKirishBinding.inflate(inflater,container,false)

        binding.materialButton.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.main_window,RoyxatdanOtish()).commit()
        }

        binding.royxatdano.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.main_window,RoyxatdanOtish2()).commit()
        }


        val list = mutableListOf<book>()
        val gson = Gson()
        object : TypeToken<List<User>>() {}.type
        val activity = activity as AppCompatActivity
        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        list.add(book("Yulduzli tunlar",R.drawable.book1,"8.2"))
        list.add(book("Urush tugasa",R.drawable.book2,"8.5"))
        list.add(book("Ikki eshik orasi",R.drawable.book3,"7.4"))
        list.add(book("Harry potter 2",R.drawable.book4,"8.6"))
        cache.edit().putString("books",gson.toJson(list)).apply()

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Kirish.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Kirish().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}