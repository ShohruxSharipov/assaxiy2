package com

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.books.book
import com.example.myapplication.Kirish
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAsosiyOynaBinding
import com.example.myapplication.databinding.FragmentNavHeaderBinding
import com.example.myapplication.databinding.FragmentSplashBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.user.User
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Splash.newInstance] factory method to
 * create an instance of this fragment.
 */
class Splash : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var currentLanguage = "En"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("CommitPrefEdits")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSplashBinding.inflate(inflater, container, false)
        val gson = Gson()
        val type = object : TypeToken<List<User>>() {}.type
            val type2 = object : TypeToken<String>() {}.type
            val activity = activity as AppCompatActivity
            val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        val handler = Handler()
        val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.animation)
        binding.process.startAnimation(anim)
        var listUser = mutableListOf<User>()

        val lan = cache.getString("lan","")
        Toast.makeText(requireContext(), "LAN : $lan", Toast.LENGTH_SHORT).show()
        if (lan != "" ){
            currentLanguage = gson.fromJson(lan,type2)
            setLocale(lan!!)
        }else{
            cache.edit().putString("lan",gson.toJson(currentLanguage)).apply()
        }



        val str = cache.getString("user", "")
        if (str.isNullOrBlank()) {
            handler.postDelayed({
                parentFragmentManager.beginTransaction().replace(R.id.main_window, Kirish())
                    .commit()
            }, 4000)
        }else{
            listUser = gson.fromJson(str,type)
            val binding2 = FragmentNavHeaderBinding.inflate(LayoutInflater.from(requireContext()),container,false)
            binding2.username.setText(listUser[0].name)
            binding2.username.setTextColor(Color.WHITE)
            handler.postDelayed({
                parentFragmentManager.beginTransaction().replace(R.id.main_window, BottomNav())
                    .commit()
            }, 4000)
        }


        return binding.root
    }

    private fun setLocale(localeName: String) {
        if (localeName != currentLanguage) {
            val locale = Locale(localeName)
            val res = resources
            val dm = res.displayMetrics
            val conf = res.configuration
            conf.locale = locale
            res.updateConfiguration(conf, dm)
            currentLanguage = localeName
            parentFragmentManager.beginTransaction().replace(R.id.bottomNav,LanguageFragment.newInstance(localeName,currentLanguage)).commit()
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
         * @return A new instance of fragment Splash.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Splash().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}