package com.example.afinal.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.afinal.Models.SignalData
import com.example.afinal.R

class SignalAdapter (private val seizuerList:ArrayList<SignalData>) : RecyclerView.Adapter<SignalAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.seizure_history_recycler,parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem =seizuerList[position]

        holder.type_of_signalBase.text=currentItem.type_of_signalBase

        holder.classificationBase.text=currentItem.classificationBase

        holder.probability_of_seizureBase.text= currentItem.probability_of_seizureBase.toString()

        holder.probability_of_notseizureBase.text= currentItem.probability_of_notseizureBase.toString()

    }

    override fun getItemCount(): Int {
        return seizuerList.size
    }


    class MyViewHolder (itemView: View) :RecyclerView.ViewHolder(itemView) {

        val type_of_signalBase: TextView = itemView.findViewById(R.id.typeOfSignal)

        val classificationBase: TextView = itemView.findViewById(R.id.classification)

        val probability_of_seizureBase: TextView = itemView.findViewById(R.id.probabilitySeizure)

        val probability_of_notseizureBase: TextView = itemView.findViewById(R.id.probabilityNon)


    }
}