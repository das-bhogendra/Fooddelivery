package com.example.fooddelivery.model

import android.os.Parcel
import android.os.Parcelable

class UserModel (
    var userId:String="",
    var FirstName:String="",
    var lastName:String="",
    var address:String="",
    var phoneNumber:String="",
    var email:String="",
    // parce3lable is used to pass data from one activity to another activity
){
    constructor(parcel: Parcel):this(
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
    )

}