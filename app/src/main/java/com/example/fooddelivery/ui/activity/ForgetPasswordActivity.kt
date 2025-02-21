package com.example.fooddelivery.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.ActivityForgetPasswordBinding
import com.example.fooddelivery.repository.UserRepositoryImpl
import com.example.fooddelivery.viewmodel.UserViewModel

class ForgetPasswordActivity : AppCompatActivity() {
    lateinit var forgetPasswordBinding: ActivityForgetPasswordBinding
    lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        forgetPasswordBinding=ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(forgetPasswordBinding.root)
        //initializing auth viewmodel
        var repo=UserRepositoryImpl()
        userViewModel=UserViewModel(repo)

        forgetPasswordBinding.btnForget.setOnClickListener{
            var email:String=forgetPasswordBinding.editEmailForget.text.toString()
            userViewModel.forgetPassword(email){success,message->
                if(success){
                    Toast.makeText(this@ForgetPasswordActivity,message,Toast.LENGTH_LONG).show()
                    finish()
                }else{
                    Toast.makeText(this@ForgetPasswordActivity, message, Toast.LENGTH_LONG).show()
                }

            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}