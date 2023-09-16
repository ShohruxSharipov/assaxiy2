package com

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.drawerlayout.widget.DrawerLayout
import com.adapter.adapter
import com.adapter.adapter_darslik
import com.books.Darslik
import com.books.book
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAsosiyOynaBinding
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.user.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Asosiy_Oyna.newInstance] factory method to
 * create an instance of this fragment.
 */
class Asosiy_Oyna : Fragment() {
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
        val binding = FragmentAsosiyOynaBinding.inflate(inflater,container,false)
        val list2 = mutableListOf<Darslik>()
        val gson = Gson()
        val type = object : TypeToken<List<book>>() {}.type
        val type2 = object : TypeToken<List<User>>() {}.type
        val activity = activity as AppCompatActivity
        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        lateinit var toggle:ActionBarDrawerToggle
        var list = listOf<book>()
        val list12 = gson.fromJson<List<User>>(cache.getString("user",""),type2)




        list2.add(Darslik(R.drawable.darslik1))
        list2.add(Darslik(R.drawable.darslik2))
        list2.add(Darslik(R.drawable.darslik3))
        list2.add(Darslik(R.drawable.darslik4))

        val str = cache.getString("books","")
        list = gson.fromJson(str,type)

        val adapter = adapter(list, object : adapter.OnClick{
            override fun click(book: book) {
                for (i in list){
                    if (i == book){
                        parentFragmentManager.beginTransaction().replace(R.id.main_window,InformationFragment.newInstance("",list.indexOf(i))).addToBackStack("orqaga").commit()
                        break
                    }
                }
            }

        })
        val adapter2 = adapter_darslik(list2)

        binding.recycleview.adapter = adapter
        binding.recycleview2.adapter = adapter2

        val drawerlayout: DrawerLayout = binding.drlayout
        val navView: NavigationView = binding.navView

        toggle = ActionBarDrawerToggle(requireActivity(),drawerlayout,R.string.open,R.string.close)
        drawerlayout.addDrawerListener(toggle)
        toggle.syncState()

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.boshsahifa -> {
                    parentFragmentManager.beginTransaction().replace(R.id.bottomNav,Asosiy_Oyna()).commit()
                }
                R.id.qidiruv -> {
                    parentFragmentManager.beginTransaction().replace(R.id.bottomNav,Search()).commit()
                }
                R.id.maqola -> {
                    parentFragmentManager.beginTransaction().replace(R.id.bottomNav,NewsFragment()).commit()
                }
                R.id.til -> {
                    parentFragmentManager.beginTransaction().replace(R.id.bottomNav,LanguageFragment()).commit()
                }
                R.id.saqlangan -> {
                    parentFragmentManager.beginTransaction().replace(R.id.bottomNav,SelectedItemsFragment()).commit()
                }
            }
            true
        }


        binding.search.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.bottomNav,Search()).commit()
        }

        return binding.root
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Asosiy_Oyna.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Asosiy_Oyna().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}