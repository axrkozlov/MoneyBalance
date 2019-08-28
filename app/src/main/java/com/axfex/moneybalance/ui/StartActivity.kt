package com.axfex.moneybalance.ui

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.LinearInterpolator
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.get
import androidx.navigation.ui.AppBarConfiguration
import androidx.transition.*
import com.axfex.moneybalance.R
import com.axfex.moneybalance.core.App
import com.axfex.moneybalance.utils.visible
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {


    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        navController = findNavController(this, R.id.start_nav_fragment)
        if ((application as App).splashShowed) {
            navController.navigate(R.id.action_to_mainActivity)
            finish()
        }
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setupWelcomePager()
        (application as App).splashShowed=true
    }

    private fun setupWelcomePager() {
        navController.addOnDestinationChangedListener { _, destination, _ ->

            val transition = TransitionSet()
                .addTransition(ChangeTransform())
                .addTransition(ChangeClipBounds())
                .addTransition(Fade())
                .addTransition(ChangeBounds())
            transition.interpolator = LinearInterpolator()
            transition.duration = 250L
            TransitionManager.beginDelayedTransition(pageIndicators, transition)

            page1.isActivated=false
            page2.isActivated=false
            page3.isActivated=false

            when (destination) {
                navController.graph[R.id.splashFragment] -> {
                }
                navController.graph[R.id.welcomeFragment] -> {
                    welcomeBackground.visible()
                    pageIndicators.visible()
                    page1.isActivated=true
                }

                navController.graph[R.id.signInFragment] -> {
                    welcomeBackground.visible()
                    pageIndicators.visible()
                    page2.isActivated=true
                }
                navController.graph[R.id.readyFragment] -> {
                    welcomeBackground.visible()
                    pageIndicators.visible()
                    page3.isActivated=true
                }

                else -> {

                }
            }
            page1.requestLayout()
            page2.requestLayout()
            page3.requestLayout()

        }
    }

//    private fun setupAppBar() {
//        setSupportActionBar(toolbar)

//
//        appBarConfiguration = AppBarConfiguration(
//            setOf(R.id.welcomeFragment, R.id.balanceFragment, R.id.splashFragment),
//            drawer_layout
//        )
//
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        nav_view.setupWithNavController(navController)
//
//    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.main, menu)
////        login.setOnClickListener {
////            navController.navigate(R.id.action_to_fragmentSignIn)
////            val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
////            drawerLayout.closeDrawer(GravityCompat.START)
////        }
//        return true
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
//    }
//
//    override fun onBackPressed() {
//        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START)
//        } else {
//            super.onBackPressed()
//        }
//    }
}
