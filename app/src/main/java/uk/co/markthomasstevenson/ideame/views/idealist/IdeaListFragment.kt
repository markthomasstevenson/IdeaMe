package uk.co.markthomasstevenson.ideame.views.idealist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_idea_list.*

import uk.co.markthomasstevenson.ideame.R
import uk.co.markthomasstevenson.ideame.viewmodels.IdeaListViewModel

class IdeaListFragment : Fragment() {
    lateinit var viewModel: IdeaListViewModel
    private lateinit var adapter: IdeaListAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = IdeaListAdapter{
            itemClicked(it)
        }
        recyclerView.adapter = adapter

        viewModel = ViewModelProviders.of(this).get(IdeaListViewModel::class.java)
        viewModel.getIdeas().observe(this, Observer {
            adapter.updateItems(ArrayList(it.map { idea -> IdeaListModel(idea.id, idea.title, idea.elevatorPitch)}))
        })
    }

    private fun itemClicked(it: String) {
        Toast.makeText(context, "Go to Edit/View Screen for $it", Toast.LENGTH_LONG).show()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_idea_list, container, false)
    }

}
