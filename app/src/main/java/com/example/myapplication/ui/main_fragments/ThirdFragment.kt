package com.example.myapplication.ui.main_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentThirdBinding

class ThirdFragment: Fragment(R.layout.fragment_third) {
    private lateinit var _binding: FragmentThirdBinding
    private var binding:FragmentThirdBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentThirdBinding.inflate(inflater, container,false)
        binding = _binding

        val view = binding?.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.let {
            it.thirdText.setOnClickListener{
                NavHostFragment.findNavController(this).navigate(R.id.action_thirdFragment_to_homeFragment2)
            }

        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

}