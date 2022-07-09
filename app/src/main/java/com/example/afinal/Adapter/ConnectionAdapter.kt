package com.example.afinal.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.afinal.Models.DoctorData
import com.example.afinal.R
import kotlinx.android.synthetic.main.list_history_of_connection.view.*

class ConnectionAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var items: List<DoctorData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_history_of_connection, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {

            is DataViewHolder -> {
                holder.bind(items.get(position))
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(blogList: List<DoctorData>){
        items = blogList
    }

    class DataViewHolder
    constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){

        val doctorName = itemView.DoctorName
        val doctorAddress = itemView.DoctorAddress
        val doctorPhone = itemView.DoctorPhone
        val doctorEmail = itemView.DoctorEmail

        fun bind(blogPost: DoctorData){


            doctorName.setText(blogPost.doctorName)
            doctorAddress.setText(blogPost.doctorAddress)
            doctorPhone.setText(blogPost.doctorPhone)
            doctorEmail.setText(blogPost.doctorEmail)

        }

    }

}