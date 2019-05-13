package uk.co.markthomasstevenson.ideame.misc

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class EmptyHandlingRecyclerView : RecyclerView {
    constructor(context: Context): super(context)
    constructor(context: Context, attrSet : AttributeSet): super(context, attrSet)
    constructor(context: Context, attrSet : AttributeSet, defStyle: Int): super(context, attrSet, defStyle)

    private lateinit var mEmptyView: View

    private fun initEmptyView() {
        mEmptyView.visibility = if (adapter == null || adapter!!.itemCount == 0) View.VISIBLE else View.GONE
        this.visibility = if (adapter == null || adapter!!.itemCount == 0) View.GONE else View.VISIBLE
    }

    private val observer: AdapterDataObserver = object : AdapterDataObserver() {
        override fun onChanged() {
            super.onChanged()
            initEmptyView()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            initEmptyView()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            initEmptyView()
        }
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        val oldAdapter = getAdapter()
        super.setAdapter(adapter)
        oldAdapter?.unregisterAdapterDataObserver(observer)
        adapter?.registerAdapterDataObserver(observer)
    }

    fun setEmptyView(view: View) {
        this.mEmptyView = view
        initEmptyView()
    }
}