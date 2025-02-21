package com.example.fooddelivery.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.ActivityNavigationBinding
import com.example.fooddelivery.ui.fragment.HomeFragment
import com.example.fooddelivery.ui.fragment.NotificationFragment
import com.example.fooddelivery.ui.fragment.ProfileFragment
import com.example.fooddelivery.ui.fragment.SearchFragment

class NavigationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNavigationBinding

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav1, fragment)
        fragmentTransaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up BottomNavigationView
        binding.sa.setOnItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.navHome -> replaceFragment(HomeFragment())
                R.id.navSearch -> replaceFragment(SearchFragment())
                R.id.navNotification -> replaceFragment(NotificationFragment())
                R.id.navProfile->replaceFragment(ProfileFragment())
                else -> {}
            }
            true
        }

        // Apply window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
