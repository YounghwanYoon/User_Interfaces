package com.example.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.snapshots.Snapshot.Companion.observe
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentListUserBinding
import com.example.myapplication.room.data.UserEntity
import com.example.myapplication.ui.adapter.UserRecycleAdapter
import com.example.myapplication.viewmodel.UserViewModel

class ListUserFragment:Fragment(R.layout.fragment_list_user){

    private val TAG:String = this.javaClass.name

    private var binding:FragmentListUserBinding? = null
    private lateinit var _binding:FragmentListUserBinding
    private lateinit var viewAdapter:UserRecycleAdapter
    private val userViewModel: UserViewModel by activityViewModels()//viewModels({ requireActivity() })
    private var layoutManager:RecyclerView.LayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListUserBinding.inflate(layoutInflater, container, false)
        binding = _binding

        registerObserver()
        registerOnClickListeners()

        return _binding.root
    }

    private fun registerOnClickListeners(){
        _binding.removeAllBtn.setOnClickListener{
            deleteAllData()

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewAdapter = UserRecycleAdapter()
        _binding.userRecyclerView.apply{

            layoutManager = LinearLayoutManager(activity)
            adapter = viewAdapter

        }
    }

    private fun registerObserver(){
        var userData:List<UserEntity>

        userViewModel.getUser().observe(viewLifecycleOwner, Observer{
            userData = it
            updateUserData(userData)
        })
    }

    private fun updateUserData(userData:List<UserEntity>?){
        viewAdapter.updateUserData(userData)
        viewAdapter.notifyDataSetChanged()
    }

    private fun deleteAllData(){
        userViewModel.apply{
            removeAllUser()
            updateUserData(null)
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}