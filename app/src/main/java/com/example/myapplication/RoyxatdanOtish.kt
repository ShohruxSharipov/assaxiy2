package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplication.databinding.FragmentRoyxatdanOtishBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RoyxatdanOtish.newInstance] factory method to
 * create an instance of this fragment.
 */
class RoyxatdanOtish : Fragment() {
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
        val binding = FragmentRoyxatdanOtishBinding.inflate(inflater,container,false)

        binding.royxatdan.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.main_window,RoyxatdanOtish2()).commit()
        }

        binding.materialButton.setOnClickListener {
            if (binding.emayil.text.toString().isBlank() || binding.paro.text.toString().isBlank()){
                Toast.makeText(requireContext(), "Hammasini to'ldiring !", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "Account topilmadi! \n Iltimos ro'yxatdan o'ting", Toast.LENGTH_SHORT).show()
            }
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
         * @return A new instance of fragment RoyxatdanOtish.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RoyxatdanOtish().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}