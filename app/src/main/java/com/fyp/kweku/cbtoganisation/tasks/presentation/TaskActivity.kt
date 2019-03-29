package com.fyp.kweku.cbtoganisation.tasks.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.databinding.ActivityTaskBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class TaskActivity : AppCompatActivity() {

    lateinit var navController: NavController
    lateinit var navHostFragment: NavHostFragment
     lateinit var binding: ActivityTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_task)
        //NavHostFragment needs to be updated with a new nav_graph when you have more than 1 graphs
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        //This will make our navController accessable from any fragment where we have a reference to mainActivity
        navController = navHostFragment.navController
        //Set up the bottom navigation bar using the line below
        NavigationUI.setupWithNavController(binding.mainBottomNavigation, navHostFragment.navController)

    }
}
