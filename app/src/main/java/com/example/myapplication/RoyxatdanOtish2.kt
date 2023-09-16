package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.Asosiy_Oyna
import com.BottomNav
import com.example.myapplication.databinding.FragmentNavHeaderBinding
import com.google.gson.Gson
import com.example.myapplication.databinding.FragmentRoyxatdanOtish2Binding
import com.google.gson.reflect.TypeToken
import com.user.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RoyxatdanOtish2.newInstance] factory method to
 * create an instance of this fragment.
 */
class RoyxatdanOtish2 : Fragment() {
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

    @SuppressLint("CommitPrefEdits")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentRoyxatdanOtish2Binding.inflate(inflater,container,false)
        val gson = Gson()
        object : TypeToken<List<User>>() {}.type
        val activity = activity as AppCompatActivity
        val cache = activity.getSharedPreferences("Cache", Context.MODE_PRIVATE)
        val edit = cache.edit()
        val users = mutableListOf<User>()


        binding.materialButton.setOnClickListener{
            if (binding.paroli.text.toString() != binding.paroli2.text.toString()){
                Toast.makeText(requireContext(), "Parollar bir biriga mos kelishi kerak !", Toast.LENGTH_SHORT).show()
            }else {
                val user = User(
                    binding.ismi.text.toString(),
                    binding.familiyasi.text.toString(),
                    binding.emaili.text.toString(),
                    binding.paroli.text.toString()
                )
                users.add(user)
            edit.putString("user",gson.toJson(users)).apply()
                parentFragmentManager.beginTransaction().replace(R.id.main_window, BottomNav())
                    .commit()

            }
        }

        binding.backarrow.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.main_window,RoyxatdanOtish()).commit()
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
         * @return A new instance of fragment Royxatdan_Otish_2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RoyxatdanOtish2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}