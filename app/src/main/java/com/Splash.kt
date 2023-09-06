package com

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.Kirish
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSplashBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.user.User

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
        val binding = FragmentSplashBinding.inflate(inflater, container, false)
        val gson = Gson()
        object : TypeToken<List<User>>() {}.type
        val activity = activity as AppCompatActivity
        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        val handler = Handler()
        val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.animation)
        binding.process.startAnimation(anim)

        val str = cache.getString("user", "")
        if (str.isNullOrBlank()) {
            handler.postDelayed({
                parentFragmentManager.beginTransaction().replace(R.id.main_window, Kirish())
                    .commit()
            }, 4000)
        }else{
            handler.postDelayed({
                parentFragmentManager.beginTransaction().replace(R.id.main_window, Asosiy_Oyna())
                    .commit()
            }, 4000)
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