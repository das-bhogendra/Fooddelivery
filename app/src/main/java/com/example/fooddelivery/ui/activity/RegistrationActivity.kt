package com.example.fooddelivery.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.ActivityRegistration2Binding
import com.example.fooddelivery.model.UserModel
import com.example.fooddelivery.repository.UserRepositoryImpl
import com.example.fooddelivery.utils.LoadingUtils
import com.example.fooddelivery.viewmodel.UserViewModel

class RegistrationActivity : AppCompatActivity() {
    lateinit var binding:ActivityRegistration2Binding
    lateinit var loadingUtils: LoadingUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityRegistration2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        loadingUtils=LoadingUtils(this)
        var repo=UserRepositoryImpl()
        var userViewModel= UserViewModel(repo)
        binding.signUp.setOnClickListener{
            var email=binding.registerEmail.text.toString()
            var password=binding.registerPassword.text.toString()
            var firstName=binding.registerFName.text.toString()
            var lastName=binding.registerLName.text.toString()
            var address=binding.registerAddress.text.toString()
            var phoneNumber=binding.registerContact.text.toString()



            userViewModel.signup(email, password)
            { success, message, userId ->
                if (success) {
                    loadingUtils.dismiss()
                    // If sign-up is successful, create a UserModel object
                    val userModel = UserModel(
                        userId.toString(),
                        firstName,
                        lastName,
                        address,
                        phoneNumber,
                        email
                    )

                    // Display a success message to the user
                    Toast.makeText(
                        this@RegistrationActivity,
                        "Registration is successful",
                        Toast.LENGTH_LONG
                    ).show()
                } else{
                    loadingUtils.dismiss()
                    // Display an error message if sign-up fails
                    Toast.makeText(
                        this@RegistrationActivity,
                        message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

           // auth.createUserWithEmailAndPassword(email,password)
              //  .addOnCompleteListener{

                     //   var userId=auth.currentUser?.uid
                       // var userModel=UserModel(
                        //    userId.toString(),firstName,lastName,address,phoneNumber,email
                      //  )
                    //    ref.child(userId.toString()).setValue(userModel)
                   // if(it.isSuccessful){Toast.makeText(
                         //   this@RegistrationActivity,"registration sucessful",Toast.LENGTH_LONG
                      //  ).show()
                   // }else{
                       //Toast.makeText(
                         //  this@RegistrationActivity,
                           // it.exception?.message,Toast.LENGTH_LONG
                      // ).show()
                 //   }
             //   }

        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}