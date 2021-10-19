package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.viewmodel.UserViewModel


class MainActivity : AppCompatActivity() {
    private val TAG:String = this.javaClass.name

    private lateinit var _binding: ActivityMainBinding
    private var binding:ActivityMainBinding? = null

    private lateinit var navHostFragment:NavHostFragment
    private lateinit var navController:NavController
    private lateinit var appBarConfig:AppBarConfiguration

    //private val userViewModel: UserViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        binding = _binding

        val view = binding?.root
        setContentView(view)

        //Register menu
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        //This remove/disable back/up button of appbar for bottom navigation
        appBarConfig = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.calendarFragment),

            //To display hamburger icon on toolbar
            _binding.drawerLayout
        )

        //replacing ActionBar title with toolbar.
        setSupportActionBar(_binding.toolbar)

        //connecting between toolbar/action bar and navcontroller but needs to do with onSupportNavigateUp()
        setupActionBarWithNavController(navController, appBarConfig)

        //connecting between bottom menu/toolbar and navcontroller
        _binding.bottomNav.setupWithNavController(navController)

        //Register drawer menu with nav_controller
        _binding.drawerNavView.setupWithNavController(navController)
    }

    //This allowing menu to register with activity appbar/toolbar and this will be shared by all fragments under this activity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation_menu, menu)
        return true
    }


    //This allow Fragment to Change base on the menu item's id match to destination id.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

       return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    /*
        Log.d(TAG, "onOptionsItemSelected:${navHostFragment.childFragmentManager.fragments} ")
        val currentFragment = navHostFragment.childFragmentManager.fragments


        return when(item.itemId){
            R.id.menu_one -> {

                //navController.navigate(R.id.action_homeFragment_to_secondFragment)
                true
            }
            R.id.menu_two ->{

                //navController.navigate(R.id.action_homeFragment_to_secondFragment)
                true
            }
            R.id.menu_three -> {

                //navController.navigate(R.id.action_homeFragment_to_thirdFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

     */

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }
}