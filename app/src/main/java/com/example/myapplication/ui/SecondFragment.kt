package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.databinding.FragmentSecondBinding

class SecondFragment: Fragment(R.layout.fragment_second) {
    private lateinit var _binding: FragmentSecondBinding

    private var binding:FragmentSecondBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container,false)
        binding = _binding
        val view = binding?.root
        return view!!
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.let {
            it.secondText.setOnClickListener{
                NavHostFragment.findNavController(this).navigate(R.id.action_secondFragment_to_thirdFragment)
            }

        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

}