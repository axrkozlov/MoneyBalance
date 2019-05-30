package com.axfex.moneybalance

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.get
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_main)

        navController = findNavController(this, R.id.garden_nav_fragment)
        setupAppBar()


    }

    private fun setupAppBar() {
        setSupportActionBar(toolbar)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination) {
                navController.graph[R.id.splashFragment] -> {
                    toolbar.visibility = View.GONE
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                }
                navController.graph[R.id.signInFragment] -> {
                    toolbar.visibility = View.GONE
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                }
                navController.graph[R.id.signUpFragment] -> {
                toolbar.visibility = View.GONE
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
                navController.graph[R.id.welcomeFragment] -> {
                    toolbar.visibility = View.GONE
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                }
                else -> {
                    toolbar.visibility = View.VISIBLE
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                }
            }
        }

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.welcomeFragment, R.id.balanceFragment, R.id.splashFragment),
            drawer_layout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
//        login.setOnClickListener {
//            navController.navigate(R.id.action_to_fragmentSignIn)
//            val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
//            drawerLayout.closeDrawer(GravityCompat.START)
//        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
