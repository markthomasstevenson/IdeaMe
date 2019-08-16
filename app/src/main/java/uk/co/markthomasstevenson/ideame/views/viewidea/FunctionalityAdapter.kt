package uk.co.markthomasstevenson.ideame.views.viewidea

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_functionality.view.*
import uk.co.markthomasstevenson.ideame.R
import uk.co.markthomasstevenson.ideame.viewmodels.IdeaViewModel
import uk.co.markthomasstevenson.ideame.views.misc.BaseViewHolder

class FunctionalityAdapter(private val viewModel: IdeaViewModel) : RecyclerView.Adapter<FunctionalityAdapter.FunctionalityViewHolder>() {
    private var items = ArrayList<FunctionalityListModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FunctionalityViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_functionality, parent, false))

    override fun onBindViewHolder(holder: FunctionalityViewHolder, position: Int) = holder.bind(items[position], viewModel)

    override fun getItemCount() = items.size

    fun updateItems(newItems: ArrayList<FunctionalityListModel>) {
        items = newItems
    }

    class FunctionalityViewHolder(itemView: View) : BaseViewHolder(itemView) {
        fun bind(item: FunctionalityListModel, viewModel: IdeaViewModel) = with(itemView) {
            usableId = item.id
            tv_func_version.setText(item.version, TextView.BufferType.NORMAL)
            tv_func_name.setText(item.name, TextView.BufferType.NORMAL)
            itemView.setOnClickListener { viewModel.functionalityClicked(usableId) }
        }
    }
}