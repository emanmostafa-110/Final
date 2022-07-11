package com.example.afinal.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.afinal.Models.DoctorData
import com.example.afinal.Models.SymptomsData
import com.example.afinal.R
import kotlinx.android.synthetic.main.list_connection_request.view.*
import kotlinx.android.synthetic.main.symptoms_recycler.view.*

class SymptomsAdapter (var myList : ArrayList<SymptomsData>) : RecyclerView.Adapter<SymptomsAdapter.ViewHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.symptoms_recycler,
            parent,false)

        return SymptomsAdapter.ViewHolder(v)
    }

    override fun onBindViewHolder(holder: SymptomsAdapter.ViewHolder, position: Int) {
        var services = myList[position]

        holder.et_1.text = services.et_1
        holder.et_2.text = services.et_2
        holder.et_3.text = services.et_3
        holder.et_4.text = services.et_4
        holder.et_5.text = services.et_5
        holder.et_6.text = services.et_6
        holder.createdAt.text=services.createdAt
    }

    override fun getItemCount(): Int {
        return myList.size
    }
    class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {

        val et_1 = itemView.et_7
        val et_2 = itemView.et_8
        val et_3 = itemView.et_9
        val et_4 = itemView.et_10
        val et_5 = itemView.et_11
        val et_6 = itemView.et_12
        val createdAt =itemView.createdAt
    }

}