package uk.co.markthomasstevenson.ideame

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.markthomasstevenson.ideame.viewmodels.IdeaViewModel

class MainActivity : BaseActivity() {

    private lateinit var viewModel: IdeaViewModel
    private var plusToTick: AnimatedVectorDrawableCompat? = null
    private var tickToPlus: AnimatedVectorDrawableCompat? = null
    private var showingPlus = true
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setStatusBar(R.color.snorlax)

        navController = findNavController(nav_host_fragment)
        val appBarConfiguration =  AppBarConfiguration.Builder(navController.graph).build()
        setupWithNavController(toolbar, navController, appBarConfiguration)

        viewModel = ViewModelProviders.of(this).get(IdeaViewModel::class.java)

        plusToTick = AnimatedVectorDrawableCompat.create(baseContext, R.drawable.plustotick)
        tickToPlus = AnimatedVectorDrawableCompat.create(baseContext, R.drawable.ticktoplus)
        viewModel.watchForNavigateEnabled().observe(this, Observer {
             morph()
        })

        fab.setImageDrawable(plusToTick)
        fab.setOnClickListener{
            viewModel.fabClicked(showingPlus)
        }

        showingPlus = true
    }

    override fun onNavigateUp(): Boolean {
        viewModel.fabClicked(showingPlus)
        return super.onNavigateUp()
    }

    fun morph() {
        if(showingPlus) {
            navController.navigate(R.id.action_ideaListFragment_to_createIdeaFragment)
        } else {
            navController.navigate(R.id.action_createIdeaFragment_to_ideaListFragment)
        }

        val drawable = if (showingPlus) plusToTick else tickToPlus
        fab.setImageDrawable(drawable)
        drawable?.start()
        showingPlus = !showingPlus
    }
}
