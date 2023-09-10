package com

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.os.bundleOf
import androidx.drawerlayout.widget.DrawerLayout
import com.adapter.adapter
import com.adapter.adapter_darslik
import com.books.Darslik
import com.books.book
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAsosiyOynaBinding
import com.google.android.material.navigation.NavigationView

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
    val list = mutableListOf<book>()
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
        lateinit var toggle:ActionBarDrawerToggle

        list2.add(Darslik(R.drawable.darslik1))
        list2.add(Darslik(R.drawable.darslik2))
        list2.add(Darslik(R.drawable.darslik3))
        list2.add(Darslik(R.drawable.darslik4))

        list.add(book("Yulduzli tunlar",R.drawable.book1,"8.2"))
        list.add(book("Urush tugasa",R.drawable.book2,"8.5"))
        list.add(book("Ikki eshik orasi",R.drawable.book3,"7.4"))
        list.add(book("Harry potter 2",R.drawable.book4,"8.6"))
        val adapter = adapter(list, object : adapter.OnClick{
            override fun click(book: book) {
                parentFragmentManager.beginTransaction().replace(R.id.main_window,InformationFragment.newInstance(book,"")).addToBackStack("orqaga").commit()
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
                    Toast.makeText(requireContext(), "Home", Toast.LENGTH_SHORT).show()
                }
                R.id.qidiruv -> {
                    Toast.makeText(requireContext(), "Settings", Toast.LENGTH_SHORT).show()
                }
                R.id.maqola -> {
                    Toast.makeText(requireContext(), "Logout", Toast.LENGTH_SHORT).show()
                }
                R.id.til -> {
                    Toast.makeText(requireContext(), "Share", Toast.LENGTH_SHORT).show()
                }
            }
            true
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