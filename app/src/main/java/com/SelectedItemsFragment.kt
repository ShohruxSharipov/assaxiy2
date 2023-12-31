package com

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adapter.adapter
import com.adapter.adapterToSearch
import com.books.book
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSelectedItemsBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SelectedItemsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SelectedItemsFragment : Fragment() {
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
    ): View {
        val binding = FragmentSelectedItemsBinding.inflate(inflater,container,false)
        val gson = Gson()
        val type = object : TypeToken<List<book>>() {}.type
        val activity = activity as AppCompatActivity
        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        var list2 = listOf<book>()
        val str = cache.getString("books","")
        list2 = gson.fromJson(str,type)
        var list = mutableListOf<book>()


        for (i in list2){
            if (i.selected){
                list.add(i)
            }
        }



        if (list.isEmpty()){
            binding.tanlanganlartext.visibility = View.VISIBLE
        }else binding.tanlanganlartext.visibility = View.INVISIBLE

        val adapter = adapterToSearch(list, object : adapter.OnClick{
            override fun click(book: book) {
                for (i in list){
                    if (i == book){
                        parentFragmentManager.beginTransaction().replace(R.id.main_window,InformationFragment.newInstance("",list2.indexOf(i))).addToBackStack("orqaga").commit()
                        break
                    }
                }
            }

        })
        binding.selectedbooks.adapter = adapter

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SelectedItemsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SelectedItemsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}