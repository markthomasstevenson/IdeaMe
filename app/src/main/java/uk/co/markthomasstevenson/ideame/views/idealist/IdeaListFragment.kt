package uk.co.markthomasstevenson.ideame.views.idealist

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_idea_list.*

import uk.co.markthomasstevenson.ideame.R
import uk.co.markthomasstevenson.ideame.misc.SwipeToDeleteHandler
import uk.co.markthomasstevenson.ideame.viewmodels.IdeaViewModel

class IdeaListFragment : Fragment() {
    lateinit var viewModel: IdeaViewModel
    private lateinit var adapter: IdeaListAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = IdeaListAdapter{
            itemClicked(it)
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteHandler(context!!) {
            it.itemId.let { ideaId ->
                val idea = viewModel.getOrCreateIdea(ideaId)
                AlertDialog.Builder(context!!)
                    .setTitle(getString(R.string.dialog_delete_idea))
                    .setMessage(getString(R.string.dialog_delete_idea_message, idea.title))
                    .setPositiveButton(R.string.dialog_sure) { dialog, _ ->
                        adapter.notifyItemRemoved(it.adapterPosition)
                        viewModel.deleteIdea(ideaId)
                        dialog.dismiss()
                    }
                    .setNegativeButton(R.string.dialog_no) { dialog, _ ->
                        adapter.notifyItemChanged(it.adapterPosition)
                        dialog.dismiss()
                    }
                    .setCancelable(false)
                    .show()
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)

        viewModel = ViewModelProviders.of(this).get(IdeaViewModel::class.java)
        viewModel.getIdeas().observe(this, Observer {
            adapter.updateItems(ArrayList(it.map { idea -> IdeaListModel(idea.id, idea.title, idea.elevatorPitch) }))
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
