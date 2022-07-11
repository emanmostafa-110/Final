package com.example.afinal.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.afinal.Models.DoctorData
import com.example.afinal.R
import kotlinx.android.synthetic.main.list_connection_request.view.*


class ConnectionAdapter (var myList : ArrayList<DoctorData>) : RecyclerView.Adapter<ConnectionAdapter.ViewHolder>() {

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener {

        fun accept_action(position: Int)

    }

    fun setonItemClickListener(listener : onItemClickListener){
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_connection_request,
            parent,false)

        return ViewHolder(v,mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var services = myList[position]

        holder.doctorName.text = services.doctorName
        holder.doctorAddress.text = services.doctorAddress
        holder.doctorPhone.text = services.doctorPhone


    }

    override fun getItemCount(): Int {
        return myList.size
    }


    class ViewHolder (itemView : View,listener : onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val doctorName = itemView.DoctorName
        val doctorAddress = itemView.DoctorAddress
        val doctorPhone = itemView.DoctorPhone

        init {

            itemView.btnAccept.setOnClickListener {
                listener.accept_action(adapterPosition)
            }

        }

    }

}