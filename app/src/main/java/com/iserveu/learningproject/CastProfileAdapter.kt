package com.iserveu.learningproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CastProfileAdapter:RecyclerView.Adapter<CastProfileAdapter.MyViewHolder>() {


    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val view  = LayoutInflater.from(parent.context).inflate(R.layout.crew_cast_layout,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    override fun getItemCount(): Int  = 15
}