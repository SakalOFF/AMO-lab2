package com.example.lab2

import android.content.pm.ActivityInfo
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity(), OnFragmentInteractionListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var graphInfo: Array<Number>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.MainFragment, R.id.GraphFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED
    }

    fun getGraphInfo(): Array<Number>? {
        return graphInfo
    }

    override fun onFragmentInteraction(arr: Array<Number>) {
        graphInfo = arr
        findNavController(R.id.nav_host_fragment).navigate(R.id.action_FirstFragment_to_SecondFragment)
    }
}