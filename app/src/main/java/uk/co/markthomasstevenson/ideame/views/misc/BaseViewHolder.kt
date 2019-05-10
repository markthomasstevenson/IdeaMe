package uk.co.markthomasstevenson.ideame.views.misc

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var usableId: String = ""
}