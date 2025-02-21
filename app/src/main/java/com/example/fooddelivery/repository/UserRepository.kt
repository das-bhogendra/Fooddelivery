package com.example.fooddelivery.repository

import com.example.fooddelivery.model.UserModel
import com.google.firebase.auth.FirebaseUser

interface UserRepository {
    //{
      //  "success":true
      //  "message": "login sucessfull"
    //"userId":"1234"
    //}
    fun login(email:String,password:String,callback:(Boolean,String)->Unit)
    fun signUp(email:String, password:String, callback: (Boolean, String, Any?) -> Unit)
    fun addUserToDatabase(userID:String,userModel: UserModel,callback: (Boolean, String) -> Unit)
    fun forgetPassword(email: String,callback: (Boolean, String) -> Unit)
    fun getCurrentUser():FirebaseUser?

}