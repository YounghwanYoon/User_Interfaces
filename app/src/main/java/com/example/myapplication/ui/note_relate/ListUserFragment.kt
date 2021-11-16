package com.example.myapplication.ui.note_relate

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentListUserBinding
import com.example.myapplication.data.repository.room.data.UserEntity
import com.example.myapplication.ui.adapter.UserRecycleAdapter
import com.example.myapplication.ui.interfaces.RecyclerviewClickListener
import com.example.myapplication.viewmodel.UserViewModel

class ListUserFragment:Fragment(R.layout.fragment_list_user), RecyclerviewClickListener{

    private val TAG:String = this.javaClass.name

    private var binding:FragmentListUserBinding? = null
    private lateinit var _binding:FragmentListUserBinding
    private lateinit var viewAdapter:UserRecycleAdapter
    private val userViewModel: UserViewModel by activityViewModels()//viewModels({ requireActivity() })
    private var layoutManager:RecyclerView.LayoutManager? = null
    private var selectedPosition:Integer? = null


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewAdapter = UserRecycleAdapter(){data_id, position -> mylistener(data_id,position)}
        _binding.userRecyclerView.apply{

            layoutManager = LinearLayoutManager(activity)
            adapter = viewAdapter

        }
    }

    private fun registerOnClickListeners(){
        _binding.removeAllBtn.setOnClickListener{
            deleteAllData()

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
    fun updateSelectedData(position:Int){
        viewAdapter.notifyItemChanged(position)

    }

    private fun deleteAllData(){
        userViewModel.apply{
            removeAllUser()
            updateUserData(null)
        }
    }

    override fun onResume() {
        selectedPosition?.let{
            updateSelectedData(selectedPosition as Int)
        }
        super.onResume()
    }

    private fun editUserGreeting(data_id:Int, position: Int){
        reusableLog("editUserGreeting", "selected data_id is", data_id)
        userViewModel.apply{
            this.selectedUser(position)
        }
        viewAdapter.notifyDataSetChanged()
    }
    override fun mylistener(data_id:Int, position: Int) {
        editUserGreeting(data_id, position)
        selectedPosition = position as Integer
        navigateToEditPage()
    }

    private fun navigateToEditPage(){
        NavHostFragment.findNavController(this).navigate(R.id.action_listUserFragment_to_editFragment)
    }


    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun reusableLog(caller:String, message:String, item:Any = ""){
        Log.d(TAG, "$caller: $message $item ")
    }
}