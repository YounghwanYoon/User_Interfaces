package com.example.myapplication.ui.adapter

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentEachUserBinding
import com.example.myapplication.room.data.UserEntity

class UserRecycleAdapter(private var userData:List<UserEntity>? = null): RecyclerView.Adapter<UserRecycleAdapter.CustomizedViewHolder>() {

    //private var userData: List<UserEntity>? = null
    private lateinit var _binding: FragmentEachUserBinding

    inner class CustomizedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        //This is where to manipulate each view in recyclerview with or without data.
        @SuppressLint("ResourceAsColor")
        fun onBind(data:UserEntity?){
            if(data != null)
                _binding.theUserData.text = data.name ?: "Data is empty or not yet updated"

            else{
                val textView = TextView(_binding.root.context)
                textView.text = "I do not see any data or something went wrong"
                textView.setTextColor(ColorStateList.valueOf(R.color.white))
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35F)
                textView.layoutParams =
                    LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                    )
                _binding.root.addView(textView)
            }
            //else display no valid item
        }
    }

    fun updateUserData(updatedData: List<UserEntity>?){
        if(updatedData != null)
            userData = updatedData
        else
            userData = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomizedViewHolder {

        val view =
            LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_each_user, parent, false)

        _binding = FragmentEachUserBinding.bind(view)

        return CustomizedViewHolder(_binding.root)
    }


    override fun onBindViewHolder(holder: CustomizedViewHolder, position: Int) {
        val data = userData?.let{it[position]}
        holder.onBind(data)
    }

    override fun getItemCount(): Int = userData?.size ?: 0


}