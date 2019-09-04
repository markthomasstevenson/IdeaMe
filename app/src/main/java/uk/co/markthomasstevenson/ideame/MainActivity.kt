package uk.co.markthomasstevenson.ideame

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.markthomasstevenson.ideame.viewmodels.IdeaViewModel
import uk.co.markthomasstevenson.ideame.views.viewidea.ViewIdeaFragment
import java.util.*

class MainActivity : BaseActivity() {

    private lateinit var viewModel: IdeaViewModel
    private var plusToTick: AnimatedVectorDrawableCompat? = null
    private var tickToPlus: AnimatedVectorDrawableCompat? = null
    private var showingPlus = true
    lateinit var navController: NavController
    var currentIdeaId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setStatusBar(R.color.snorlax)

        setSupportActionBar(toolbar)
        navController = findNavController(nav_host_fragment)
        setupActionBarWithNavController(this, navController)

        viewModel = ViewModelProviders.of(this).get(IdeaViewModel::class.java)

        plusToTick = AnimatedVectorDrawableCompat.create(baseContext, R.drawable.plustotick)
        tickToPlus = AnimatedVectorDrawableCompat.create(baseContext, R.drawable.ticktoplus)
        viewModel.watchForNavigateEnabled().observe(this, Observer {
            if(showingPlus) {
                val bundle = bundleOf(ViewIdeaFragment.IDEA_ID to UUID.randomUUID().toString())
                navController.navigate(R.id.action_ideaListFragment_to_createIdeaFragment, bundle)
            } else {
                if(navController.currentDestination?.id == R.id.editIdeaFragment) {
                    navController.navigate(R.id.action_editIdeaFragment_to_ideaListFragment)
                } else {
                    navController.navigate(R.id.action_createIdeaFragment_to_ideaListFragment)
                }
            }
             morph()
        })
        viewModel.watchForEditableIdeaClicked().observe(this, Observer {
            val bundle = bundleOf(ViewIdeaFragment.IDEA_ID to it)
            navController.navigate(R.id.action_ideaListFragment_to_editIdeaFragment, bundle)
            morph()
        })

        fab.setImageDrawable(plusToTick)
        fab.setOnClickListener{
            viewModel.fabClicked(showingPlus)
        }

        showingPlus = true
    }

    override fun onSupportNavigateUp(): Boolean {
        if(navController.currentDestination?.id == R.id.createIdeaFragment) {
            val alert = this.let { AlertDialog.Builder(it) }
            alert.setTitle(R.string.undo_idea_create_title)
                    .setMessage(R.string.undo_idea_create_message)
                    .setPositiveButton(R.string.dialog_delete_idea
                    ) { dialog, _ ->
                        viewModel.deleteIdea(currentIdeaId!!)
                        viewModel.enableNavigation()
                        dialog.dismiss()
                    }
                    .setNegativeButton(R.string.dialog_cancel
                    ) { dialog, _ ->
                        dialog.dismiss()
                    }
            alert.show()
        } else if(navController.currentDestination?.id == R.id.editIdeaFragment) {
            viewModel.enableNavigation()
        }
        return false
    }

    override fun onBackPressed() {
        viewModel.fabClicked(showingPlus)
    }
    override fun onNavigateUp(): Boolean {
        viewModel.fabClicked(showingPlus)
        return false
    }

    fun morph() {
        val drawable = if (showingPlus) plusToTick else tickToPlus
        fab.setImageDrawable(drawable)
        drawable?.start()
        showingPlus = !showingPlus
    }
}
