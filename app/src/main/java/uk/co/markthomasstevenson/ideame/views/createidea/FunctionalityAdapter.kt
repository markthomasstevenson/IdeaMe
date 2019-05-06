package uk.co.markthomasstevenson.ideame.views.idealist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_functionality.view.*
import uk.co.markthomasstevenson.ideame.R
import uk.co.markthomasstevenson.ideame.misc.afterTextChanged

class FunctionalityAdapter(private val versionListener: (String, String) -> Unit, private val nameListener: (String, String) -> Unit) : RecyclerView.Adapter<FunctionalityAdapter.FunctionalityViewHolder>() {
    private var items = ArrayList<FunctionalityListModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FunctionalityViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_functionality, parent, false))

    override fun onBindViewHolder(holder: FunctionalityViewHolder, position: Int) = holder.bind(items[position], versionListener, nameListener)

    override fun getItemCount() = items.size

    fun updateItems(newItems: ArrayList<FunctionalityListModel>) {
        items = newItems
        notifyDataSetChanged()
    }

    class FunctionalityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: FunctionalityListModel, versionListener: (String, String) -> Unit, nameListener: (String, String) -> Unit) = with(itemView) {
            tv_func_version.setText(item.version, TextView.BufferType.EDITABLE)
            tv_func_name.setText(item.name, TextView.BufferType.EDITABLE)
            tv_func_version.afterTextChanged { versionListener(item.id, it) }
            tv_func_name.afterTextChanged { nameListener(item.id, it) }
        }
    }
}