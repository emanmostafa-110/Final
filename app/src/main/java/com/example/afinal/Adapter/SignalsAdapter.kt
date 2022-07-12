package com.example.afinal.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.afinal.Models.DoctorData
import com.example.afinal.Models.SignalData
import com.example.afinal.R
import kotlinx.android.synthetic.main.list_connection_request.view.*
import kotlinx.android.synthetic.main.seizure_history_recycler.view.*

class SignalsAdapter (var myList : ArrayList<SignalData>) : RecyclerView.Adapter<SignalsAdapter.ViewHolder>() {

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener {

        fun btn_deleteSignal(position: Int)

    }

    fun setonItemClickListener(listener : onItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.seizure_history_recycler,
            parent,false)

        return ViewHolder(v, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var services = myList[position]

        holder.type.text = services.type_of_signalBase
        holder.classification.text = services.classificationBase
        holder.propSeizure.text = services.probability_of_seizureBase
        holder.propNonSeizure.text = services.probability_of_notseizureBase
    }

    override fun getItemCount(): Int {
        return myList.size
    }
    class ViewHolder (itemView : View, listener : onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val type = itemView.typeOfSignal
        val classification = itemView.classification
        val propSeizure = itemView.probabilitySeizure
        val propNonSeizure = itemView.probabilityNon

        init {

            itemView.btn_delete.setOnClickListener {
                listener.btn_deleteSignal(adapterPosition)
            }

        }

    }
}