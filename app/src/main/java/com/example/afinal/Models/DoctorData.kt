package com.example.afinal.Models

class DoctorData (
                  var doctorName: String,

                  var doctorAddress: String,

                  var doctorPhone: String,

                  var doctorEmail: String,

                  var doctorId :Int,

                  var connectionId :Int
                  )


{
    override fun toString(): String {

       return "doctorData(name='$doctorName', address='$doctorAddress', phone='$doctorPhone', email='$doctorEmail'" + " ,id='$connectionId' ,doctor_id='$doctorId')"
        // return "doctorData(name='$doctorName', address='$doctorAddress', phone='$doctorPhone', email='$doctorEmail'"
    }


}