package com.example.myapplication.ui.main_fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.ui.adapter.RepoRecyclerAdapter
import com.example.myapplication.viewmodel.RepoViewModel

class HomeFragment: Fragment(R.layout.fragment_home) {

    private lateinit var _binding:FragmentHomeBinding
    private var binding:FragmentHomeBinding? = null
    private lateinit var recyclerviewAdapter:RepoRecyclerAdapter

    private val repoViewModel by activityViewModels<RepoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Register menu
        setHasOptionsMenu(true)

        _binding = FragmentHomeBinding.inflate(inflater, container,false)
        binding = _binding

        registerNavHostFragment()
        initRecyclerView()
        registerObserver()

        val view = binding?.root
        return view!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initRecyclerView(){
        recyclerviewAdapter = RepoRecyclerAdapter()
        val layoutManager = LinearLayoutManager(this@HomeFragment.context)
        _binding.apply{
            this.repoRecyclerview.apply{
                this.layoutManager = layoutManager
                adapter = recyclerviewAdapter
            }
        }
    }

    private fun registerObserver(){
        repoViewModel.repoData.observe(viewLifecycleOwner, Observer {

            if(it.data != null){
                //recyclerviewAdapter.setData(it.data)
            }
        })


        repoViewModel.testingData()
    }

    private fun registerNavHostFragment(){
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