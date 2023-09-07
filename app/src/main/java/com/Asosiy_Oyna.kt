package com

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adapter.adapter
import com.adapter.adapter_darslik
import com.books.Darslik
import com.books.book
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAsosiyOynaBinding

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

        list2.add(Darslik(R.drawable.darslik1))
        list2.add(Darslik(R.drawable.darslik2))
        list2.add(Darslik(R.drawable.darslik3))
        list2.add(Darslik(R.drawable.darslik4))

        list.add(book("Yulduzli tunlar",R.drawable.book1,"8.2"))
        list.add(book("Urush tugasa",R.drawable.book2,"8.5"))
        list.add(book("Ikki eshik orasi",R.drawable.book3,"7.4"))
        list.add(book("Harry potter 2",R.drawable.book4,"8.6"))
        val adapter = adapter(list)
        val adapter2 = adapter_darslik(list2)

        binding.recycleview.adapter = adapter
        binding.recycleview2.adapter = adapter2

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