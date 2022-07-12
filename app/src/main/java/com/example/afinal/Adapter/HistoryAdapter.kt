package com.example.afinal.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.afinal.Models.DoctorData
import com.example.afinal.R
import kotlinx.android.synthetic.main.list_connection_request.view.*
import kotlinx.android.synthetic.main.list_connection_request.view.DoctorAddress
import kotlinx.android.synthetic.main.list_connection_request.view.DoctorName
import kotlinx.android.synthetic.main.list_connection_request.view.DoctorPhone
import kotlinx.android.synthetic.main.list_historyt_of_connection.view.*

class HistoryAdapter (var myList2 : ArrayList<DoctorData>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>()  {

    private lateinit var mListener2 : onItemClickListener

    interface onItemClickListener {

        fun delete_action(position: Int)

    }

    fun setonItemClickListener(listener : onItemClickListener){
        mListener2 = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_historyt_of_connection,
            parent,false)

        return ViewHolder(v, mListener2)
    }


    override fun onBindViewHolder(holder: HistoryAdapter.ViewHolder, position: Int) {
        var services = myList2[position]

        holder.doctorName.text = services.doctorName
        holder.doctorAddress.text = services.doctorAddress
        holder.doctorPhone.text = services.doctorPhone
    }


    override fun getItemCount(): Int {
        return myList2.size
    }
    class ViewHolder (itemView : View, listener : HistoryAdapter.onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val doctorName = itemView.DoctorName
        val doctorAddress = itemView.DoctorAddress
        val doctorPhone = itemView.DoctorPhone

        init {

            itemView.btnDelete.setOnClickListener {
                listener.delete_action(adapterPosition)
            }

        }

    }
}