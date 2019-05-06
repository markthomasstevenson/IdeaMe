package uk.co.markthomasstevenson.ideame.views.idealist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_idea.view.*
import uk.co.markthomasstevenson.ideame.R

class IdeaListAdapter(private val listener: (String) -> Unit) : RecyclerView.Adapter<IdeaListAdapter.IdeaViewHolder>() {
    private var items = ArrayList<IdeaListModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = IdeaViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_idea, parent, false))

    override fun onBindViewHolder(holder: IdeaViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount() = items.size

    fun updateItems(newItems: ArrayList<IdeaListModel>) {
        items = newItems
        notifyDataSetChanged()
    }

    class IdeaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: IdeaListModel, listener: (String) -> Unit) = with(itemView) {
            idea_name.text = item.title
            idea_pitch.text = item.elavatorPitch
            setOnClickListener { listener(item.id) }
        }
    }
}