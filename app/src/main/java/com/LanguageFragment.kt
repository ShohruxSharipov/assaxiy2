package com

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.adapter.AdapterLang
import com.adapter.adapter
import com.adapter.adapterToSearch
import com.books.Language
import com.books.book
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentLanguageBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LanguageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LanguageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val gson = Gson()
    val type2 = object : TypeToken<String>() {}.type

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

      private var currentLanguage = "En"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLanguageBinding.inflate(inflater,container,false)
        val list = mutableListOf<Language>()
        val activity = activity as AppCompatActivity
        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        val lan = cache.getString("lan","")
        if (lan != ""){
            currentLanguage = lan!!
        }
        Toast.makeText(requireContext(), currentLanguage, Toast.LENGTH_SHORT).show()

        list.add(Language(R.drawable.img,"English"))
        list.add(Language(R.drawable.img_1,"Руский"))
        list.add(Language(R.drawable.img_2,"O'zbek"))
        list.add(Language(R.drawable.img_3,"Spain"))
        list.add(Language(R.drawable.img_4,"Korean"))

        var adapter =AdapterLang(list,object :AdapterLang.clickLan{
            override fun SwichLanguage(language: Language) {
                when(language.name){
                    "English" -> setLocale("En")
                    "Руский" -> setLocale("Ru")
                    "O'zbek" -> setLocale("Uz")
                }
            }

        })
        binding.til.adapter = adapter

        binding.tilqidir.addTextChangedListener {
            val itemFilter = mutableListOf<Language>()
            if (it!!.isNotEmpty() && it.isNotBlank()){
                for (i in list){
                    if (i.name.contains(it)){
                        itemFilter.add(i)
                    }
                }
                adapter = AdapterLang(itemFilter,object :AdapterLang.clickLan{
                    override fun SwichLanguage(language: Language) {
                        when(language.name){
                            "English" -> setLocale("En")
                            "Руский" -> setLocale("Ru")
                            "O'zbek" -> setLocale("Uz")
                        }
                    }
                })

            }else adapter = AdapterLang(list,object :AdapterLang.clickLan{
                override fun SwichLanguage(language: Language) {
                    when(language.name){
                        "English" -> setLocale("En")
                        "Руский" -> setLocale("Ru")
                        "O'zbek" -> setLocale("Uz")
                    }
                }

            })

            binding.til.adapter = adapter
        }

        return binding.root
    }


    private fun setLocale(localeName: String) {
        val activity = activity as AppCompatActivity
        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        if (localeName != currentLanguage) {
            val locale = Locale(localeName)
            val res = resources
            val dm = res.displayMetrics
            val conf = res.configuration
            conf.locale = locale
            res.updateConfiguration(conf, dm)
            cache.edit().putString("lan",localeName).apply()
            currentLanguage = localeName
            parentFragmentManager.beginTransaction().replace(R.id.bottomNav,LanguageFragment()).commit()
        } else {
            Toast.makeText(
                requireActivity(), "Language, , already, , selected)!", Toast.LENGTH_SHORT).show();
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LanguageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LanguageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}