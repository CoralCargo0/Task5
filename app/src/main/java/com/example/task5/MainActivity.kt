package com.example.task5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.host_fragment) as NavHostFragment? ?: return

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.mainFragment)
        )
        val navController = host.navController

        setupActionBarWithNavController(navController)
    }


//    override fun onSupportNavigateUp(): Boolean {
//        return findNavController(R.id.host_fragment).navigateUp(appBarConfiguration)
//    }
//
//    override fun onContextItemSelected(item: MenuItem): Boolean {
//        findNavController(R.id.home_dest).navigate(R.id.action_home_dest_to_sort_dest, null)
//        return super.onContextItemSelected(item)
//    }


}