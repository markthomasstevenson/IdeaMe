package uk.co.markthomasstevenson.ideame.views.viewidea

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_view_idea.*

import uk.co.markthomasstevenson.ideame.R
import uk.co.markthomasstevenson.ideame.misc.SwipeToDeleteHandler
import uk.co.markthomasstevenson.ideame.misc.afterTextChanged
import uk.co.markthomasstevenson.ideame.model.Functionality
import uk.co.markthomasstevenson.ideame.viewmodels.IdeaViewModel
import java.util.*
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import uk.co.markthomasstevenson.ideame.MainActivity


/**
 * A simple [Fragment] subclass.
 */
class ViewIdeaFragment : Fragment() {
    companion object {
        const val IDEA_ID = "IDEA_ID"
    }

    private lateinit var viewModel: IdeaViewModel
    private lateinit var adapter: FunctionalityAdapter
    private lateinit var ideaId: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(activity!!).get(IdeaViewModel::class.java)
        adapter = FunctionalityAdapter(viewModel)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteHandler(context!!) {
            viewModel.deleteFunctionality(it.usableId)
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)
        recyclerView.setHasFixedSize(false)
        var existingIdeaId = arguments?.getString(IDEA_ID)
        if(existingIdeaId == null) {
            existingIdeaId = UUID.randomUUID().toString()
        }
        ideaId = existingIdeaId
        (activity as MainActivity).currentIdeaId = ideaId

        val idea = viewModel.getOrCreateIdea(ideaId)
        tv_title.setText(idea.title)
        tv_elevatorPitch.setText(idea.elevatorPitch)
        viewModel.getFunctionalities(ideaId).observe(this, Observer {
            adapter.updateItems(ArrayList(it.map { functionality -> FunctionalityListModel(functionality.id, functionality.version, functionality.name, functionality.ideaId) }))
            adapter.notifyDataSetChanged()
        })

        btn_add_functionality.setOnClickListener { showAddFunctionalityDialog() }
        tv_elevatorPitch.afterTextChanged {
            et_elevatorPitch_container.error = null
        }
        tv_title.afterTextChanged {
            et_title_container.error = null
        }
        viewModel.watchFabWasClickedToCreate().observe(this, Observer {
            if(!it) {
                var error = false
                if(tv_title.text.toString().trim().isBlank()) {
                    et_title_container.error = getString(R.string.create_idea_title_error)
                    error = true
                }
                if(tv_elevatorPitch.text.toString().trim().isBlank()) {
                    et_elevatorPitch_container.error = getString(R.string.create_idea_elevator_pitch_error)
                    error = true
                }
                if(!error) {
                    saveInput()
                    viewModel.enableNavigation()
                }
            }
        })
        viewModel.watchForFunctionalityClicked().observe(this, Observer {
            if(it != null) {
                val factory = LayoutInflater.from(context)
                val functionality = viewModel.getFunctionality(it)

                val textEntryView = factory.inflate(R.layout.view_functionality_entry, null)
                textEntryView.findViewById<EditText>(R.id.tv_version).setText(functionality?.version)
                textEntryView.findViewById<EditText>(R.id.tv_functionality).setText(functionality?.name)

                val alert = context?.let { AlertDialog.Builder(it) }
                alert?.setTitle(R.string.create_func_title)
                        ?.setView(textEntryView)
                        ?.setPositiveButton(R.string.dialog_update
                        ) { dialog, _ ->
                            functionalityEdited(it, textEntryView.findViewById<EditText>(R.id.tv_version).text.toString().trim(),
                                    textEntryView.findViewById<EditText>(R.id.tv_functionality).text.toString().trim())
                            dialog.dismiss()
                        }
                        ?.setNegativeButton(R.string.dialog_cancel
                        ) { dialog, _ ->
                            dialog.dismiss()
                        }
                alert?.show()
                viewModel.functionalityClicked(null)
            }
        })
    }

    private fun showAddFunctionalityDialog() {
        val factory = LayoutInflater.from(context)

        val textEntryView = factory.inflate(R.layout.view_functionality_entry, null)

        val alert = context?.let { AlertDialog.Builder(it) }
        alert?.setTitle(R.string.create_func_title)
                ?.setView(textEntryView)
                ?.setPositiveButton(R.string.dialog_create
                ) { dialog, _ ->
                    addFunctionality(textEntryView.findViewById<EditText>(R.id.tv_version).text.toString().trim(),
                            textEntryView.findViewById<EditText>(R.id.tv_functionality).text.toString().trim())
                    dialog.dismiss()
                }
                ?.setNegativeButton(R.string.dialog_cancel
                ) { dialog, _ ->
                    dialog.dismiss()
                }
        alert?.show()
    }

    private fun saveInput() {
        viewModel.updateIdea(ideaId, tv_title.text.toString().trim(), tv_elevatorPitch.text.toString().trim())
    }

    private fun addFunctionality(version: String, name: String) {
        viewModel.addFunctionality(ideaId, version, name)
    }

    private fun functionalityEdited(id: String, version: String, name: String) {
        viewModel.updateFunctionalityVersion(id, version, name)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_idea, container, false)
    }


}
