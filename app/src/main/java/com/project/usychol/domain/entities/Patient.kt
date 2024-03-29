package com.project.usychol.domain.entities

import com.google.gson.annotations.SerializedName

data class Patient (
    var id: String?,

    @SerializedName("profilePicture")
    var image: String?,

    var name: String,
    var appointmentCount: Int,
    var patientClass: String,
    var motherName: String,
    var patientSummary: String,
    var fatherName: String,
    var maritalStatus: String,
    var age: String?,
    var fromUser: String,
    var reports: ArrayList<Report>?
){
    init {
        if(id == null){
            id = (1..10000).random().toString()
        }
    }
}
