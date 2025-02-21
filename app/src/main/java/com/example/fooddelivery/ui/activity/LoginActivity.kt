package com.example.fooddelivery.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.ActivityLogin2Binding

class LoginActivity : AppCompatActivity() {
    lateinit var binding:ActivityLogin2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignupnavigate.setOnClickListener(){
            val intent=Intent(this@LoginActivity,RegistrationActivity::class.java)
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}