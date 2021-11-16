package com.example.myapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.repository.room.data.RepoEntity
import com.example.myapplication.databinding.FragmentHomeRecyclerviewItemBinding

class RepoRecyclerAdapter(): RecyclerView.Adapter<RepoRecyclerAdapter.RepoCustomizedViewHolder>() {
    private val TAG:String = this.javaClass.name

    private lateinit var _binding:FragmentHomeRecyclerviewItemBinding
    private var data:List<RepoEntity>? = null

    init{
        data = mutableListOf(
            RepoEntity(1,"testing.com", "Testing Name"),
            RepoEntity(2,"testing.com", "Testing Name"),
            RepoEntity(3,"testing.com", "Testing Name"),
        )
    }

    fun setData(newData:List<RepoEntity>){
        this.data = newData
    }


    inner class RepoCustomizedViewHolder(_binding:FragmentHomeRecyclerviewItemBinding):RecyclerView.ViewHolder(_binding.root){

        fun onBind(each_data:RepoEntity){
            _binding.eachRepoItemTxtview.text = each_data.full_name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoCustomizedViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_home_recyclerview_item, parent, false)

        _binding = FragmentHomeRecyclerviewItemBinding.bind(view)
        return RepoCustomizedViewHolder(_binding)
    }

    override fun onBindViewHolder(holder: RepoCustomizedViewHolder, position: Int) {

        data?.let{
            holder.onBind(data!!.get(position))
        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }
}