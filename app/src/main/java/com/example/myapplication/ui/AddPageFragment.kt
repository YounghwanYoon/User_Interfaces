package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAddPageBinding
import com.example.myapplication.room.data.UserEntity
import com.example.myapplication.viewmodel.UserViewModel

class AddPageFragment : Fragment(R.layout.fragment_add_page) {

    private val TAG:String = this.javaClass.name
    private lateinit var userViewModel: UserViewModel
    private lateinit var _binding:FragmentAddPageBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        _binding = FragmentAddPageBinding.inflate(inflater)

        _binding.addBtn.setOnClickListener{
            val userName = _binding.newUser.text.toString()
            addUser(userName)

            NavHostFragment.findNavController(this).navigate(R.id.action_addPageFragment_to_listUserFragment)
        }

        return _binding.root
    }

    private fun addUser(userName:String){
        inputValidation()
        val newUserEntity = UserEntity(0,userName,"hello")
        userViewModel.addUser(newUserEntity)
    }

    private fun inputValidation():Boolean{

        return true
    }




}