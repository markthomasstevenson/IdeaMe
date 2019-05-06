package uk.co.markthomasstevenson.ideame.views.createidea


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_create_idea.*

import uk.co.markthomasstevenson.ideame.R
import uk.co.markthomasstevenson.ideame.misc.afterTextChanged
import uk.co.markthomasstevenson.ideame.viewmodels.IdeaViewModel
import uk.co.markthomasstevenson.ideame.views.idealist.FunctionalityAdapter
import uk.co.markthomasstevenson.ideame.views.idealist.FunctionalityListModel
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class CreateIdeaFragment : Fragment() {

    private lateinit var viewModel: IdeaViewModel
    private lateinit var adapter: FunctionalityAdapter
    private lateinit var ideaId: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        adapter = FunctionalityAdapter(::versionEdited, ::nameEdited)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        ideaId = UUID.randomUUID().toString()

        viewModel = ViewModelProviders.of(this).get(IdeaViewModel::class.java)
        viewModel.getOrCreateIdea(ideaId)
        viewModel.getFunctionalities(ideaId).observe(this, androidx.lifecycle.Observer {
            adapter.updateItems(ArrayList(it.map { functionality -> FunctionalityListModel(functionality.id, functionality.version, functionality.name) }))
        })

        btn_add_functionality.setOnClickListener { addFunctionality() }
        tv_elevatorPitch.afterTextChanged { saveInput() }
        tv_title.afterTextChanged { saveInput() }
    }

    private fun saveInput() {
        viewModel.updateIdea(ideaId, tv_title.text.toString(), tv_elevatorPitch.text.toString())
    }

    private fun addFunctionality() {
        viewModel.addFunctionality()
    }

    private fun nameEdited(id: String, name: String) {
        viewModel.updateFunctionalityName(id, name)
    }

    private fun versionEdited(id: String, version: String) {
        viewModel.updateFunctionalityVersion(id, version)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_idea, container, false)
    }


}
