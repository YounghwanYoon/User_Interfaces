package com.example.myapplication.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment: Fragment(R.layout.fragment_home) {

    private lateinit var _binding:FragmentHomeBinding
    private var binding:FragmentHomeBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Register menu
        setHasOptionsMenu(true)

        _binding = FragmentHomeBinding.inflate(inflater, container,false)
        binding = _binding
        val view = binding?.root
        return view!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.let {
            it.homeText.setOnClickListener{
                NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_secondFragment)
            }
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}