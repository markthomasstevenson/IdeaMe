package uk.co.markthomasstevenson.ideame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var plusToTick: AnimatedVectorDrawableCompat? = null
    private var tickToPlus: AnimatedVectorDrawableCompat? = null
    private var showingPlus = true
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(nav_host_fragment)
        val appBarConfiguration =  AppBarConfiguration.Builder(navController.graph).build()
        setupWithNavController(toolbar, navController, appBarConfiguration)

        plusToTick = AnimatedVectorDrawableCompat.create(baseContext, R.drawable.plustotick)
        tickToPlus = AnimatedVectorDrawableCompat.create(baseContext, R.drawable.ticktoplus)
        fab.setImageDrawable(plusToTick)
        fab.setOnClickListener{
            if(showingPlus) {
                navController.navigate(R.id.action_ideaListFragment_to_createIdeaFragment)
            } else {
                navController.navigate(R.id.action_createIdeaFragment_to_ideaListFragment)
            }
            morph()
        }
        showingPlus = true
    }

    fun morph() {
        val drawable = if (showingPlus) plusToTick else tickToPlus
        fab.setImageDrawable(drawable)
        drawable?.start()
        showingPlus = !showingPlus
    }
}
