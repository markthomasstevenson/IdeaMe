package uk.co.markthomasstevenson.ideame.views.idealist

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_functionality.view.*
import uk.co.markthomasstevenson.ideame.R
import uk.co.markthomasstevenson.ideame.views.misc.BaseViewHolder

class FunctionalityAdapter(private val versionListener: (String, String) -> Unit, private val nameListener: (String, String) -> Unit) : RecyclerView.Adapter<FunctionalityAdapter.FunctionalityViewHolder>() {
    private var items = ArrayList<FunctionalityListModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FunctionalityViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_functionality, parent, false))

    override fun onBindViewHolder(holder: FunctionalityViewHolder, position: Int) = holder.bind(items[position], versionListener, nameListener)

    override fun getItemCount() = items.size

    fun updateItems(newItems: ArrayList<FunctionalityListModel>) {
        items = newItems
    }

    class FunctionalityViewHolder(itemView: View) : BaseViewHolder(itemView) {
        private lateinit var nListener: (String, String) -> Unit
        private lateinit var itemIdContainer: String
        private lateinit var vListener: (String, String) -> Unit
        private val tv_func_text_watcher = object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                vListener(itemIdContainer, editable.toString())
            }
        }

        private val tv_func_name_text_watcher = object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                nListener(itemIdContainer, editable.toString())
            }
        }

        fun bind(item: FunctionalityListModel, versionListener: (String, String) -> Unit, nameListener: (String, String) -> Unit) = with(itemView) {
            vListener = versionListener
            nListener = nameListener
            itemIdContainer = item.ideaId
            usableId = item.id

            tv_func_version.removeTextChangedListener(tv_func_text_watcher)
            tv_func_name.removeTextChangedListener(tv_func_name_text_watcher)

            tv_func_version.setText(item.version, TextView.BufferType.EDITABLE)
            tv_func_name.setText(item.name, TextView.BufferType.EDITABLE)
            tv_func_version.addTextChangedListener(tv_func_text_watcher)
            tv_func_name.addTextChangedListener(tv_func_name_text_watcher)
        }
    }
}