package com.example.fooddelivery.viewmodel

import com.example.fooddelivery.model.UserModel



import com.example.fooddelivery.repository.UserRepository
import com.google.firebase.auth.FirebaseUser

class UserViewModel(val repo: UserRepository) {
    fun login(email:String,password:String,callback:(Boolean,String)->Unit){
        repo.login(email,password,callback)

    }
    fun signup(email: String, password: String, callback: (Boolean, String, Any?) -> Unit) {
        repo.signUp(email,password,callback)

    }
    fun addUserToDatabase(userID:String, userModel: UserModel, callback: (Boolean, String) -> Unit){
        repo.addUserToDatabase(userID,userModel,callback)

    }
    fun forgetPassword(email: String,callback: (Boolean, String) -> Unit){
        repo.forgetPassword(email,callback)

    }
    fun getCurrentUser(): FirebaseUser?{
        return repo.getCurrentUser()

    }



}