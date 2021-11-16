package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.databinding.FragmentEditBinding
import com.example.myapplication.data.repository.room.data.UserEntity
import com.example.myapplication.viewmodel.UserViewModel

class EditFragment : Fragment() {
    private val TAG:String = this.javaClass.name
    private lateinit var binding: FragmentEditBinding
    private var _binding:FragmentEditBinding? = null
    private val viewModel:UserViewModel by activityViewModels()
    private var data: UserEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEditBinding.inflate(inflater,container,false)
        _binding = binding

        registerObserver()
        return binding.root
    }

    private fun registerObserver(){

        Log.d(TAG, "registerObserver: under registerObserver")

        viewModel.selectedUser.observe(viewLifecycleOwner, Observer {
            data = it
            binding.apply {
                data?.let {
                    this.showUserId.text = it.user_id.toString()
                    this.editUserName.setText(it.name)
                    this.editUserGreeting.setText(it.greeting)
                }
                this.submitBtn.setOnClickListener {
                    viewModel.editUser(
                        data!!.user_id,
                        this.editUserName.text.toString(),
                        this.editUserGreeting.text.toString()
                    )
                    navigation()
                }
            }
        })
    }

    private fun logData(data:Any){
        Log.d(TAG, "logData: ${data}")
    }

    private fun navigation(){
        NavHostFragment.findNavController(this).navigate(R.id.action_editFragment_to_listUserFragment)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
    }