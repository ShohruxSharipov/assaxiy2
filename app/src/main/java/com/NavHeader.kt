package com

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Display.Mode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentNavHeaderBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.user.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NavHeader.newInstance] factory method to
 * create an instance of this fragment.
 */
class NavHeader : Fragment() {
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
        val binding = FragmentNavHeaderBinding.inflate(inflater,container,false)
        val activity = activity as AppCompatActivity
        val cache = activity.getSharedPreferences("Cache",Context.MODE_PRIVATE)
        val type = object : TypeToken<List<User>>() {}.type
        val gson = Gson()
        val user = gson.fromJson<List<User>>(cache.getString("user",""),type)
        binding.imageView12.setOnClickListener {
            Log.d("TAG", "User: ${user[0].name}")
            Toast.makeText(requireContext(), "sdfsdfsdf", Toast.LENGTH_SHORT).show()
        }


        binding.textView21.text = user[0].name
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NavHeader.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NavHeader().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}