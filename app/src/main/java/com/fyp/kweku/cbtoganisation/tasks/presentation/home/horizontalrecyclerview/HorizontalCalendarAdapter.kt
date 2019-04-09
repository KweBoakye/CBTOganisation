package com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.databinding.HorizontalCalendarItemBinding
import timber.log.Timber


class HorizontalCalendarAdapter(private val context: Context,
                                private val onDaySelectedListener: HorizontalCalendarAdapter.OnDaySelectedListener,
                                val onEndReachedListener: OnEndReachedListener): RecyclerView.Adapter<HorizontalCalendarAdapter.HorizontalCalendarViewHolder>() {
    private lateinit var data: MutableList<HorizontalCalendarItem>
    private var bottomAdvanceCallback = 0

    lateinit var viewHolderBinding: HorizontalCalendarItemBinding
    private var isFirstBind: Boolean = true

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HorizontalCalendarAdapter.HorizontalCalendarViewHolder {
        viewHolderBinding  = DataBindingUtil.inflate( LayoutInflater.from(parent.context), R.layout.horizontal_calendar_item,
            parent,
            false)
        val horizontalCalendarViewHolder = HorizontalCalendarAdapter.HorizontalCalendarViewHolder( viewHolderBinding.root)
        horizontalCalendarViewHolder.day = viewHolderBinding.calendarDay
        horizontalCalendarViewHolder.month = viewHolderBinding.calendarMonth
        horizontalCalendarViewHolder.year = viewHolderBinding.calendarYear
        horizontalCalendarViewHolder.itemLayout = viewHolderBinding.calendarItem
        horizontalCalendarViewHolder.dateLayout = viewHolderBinding.dateLayout
        return horizontalCalendarViewHolder
    }

    override fun getItemCount(): Int {
        return data.size
    }



    override fun onBindViewHolder(holder: HorizontalCalendarAdapter.HorizontalCalendarViewHolder, position: Int) {

        if (position == 0 && !isFirstBind) {notifyEndReached()}
        else if ((position + bottomAdvanceCallback) >= (itemCount-1)){notifyStartReached()}
        isFirstBind = false
        holder.day.text = data[position].day.toString()
        holder.month.text = HorizontalCalendarUtils.returnMonthName(data[position].month)
        holder.year.text = data[position].year.toString()
        holder.itemLayout.setOnClickListener{view -> onDaySelectedListener.onDaySelected(view,
            HorizontalCalendarUtils.returnStringDate(data[position].day,data[position].month, data[position].year), position)
        Timber.i("${data[position].month}")}

        HorizontalCalendarUtils.configCallLayout(context,holder.dateLayout,data[position].backgroundColor )

    }

    class HorizontalCalendarViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
    lateinit var day: TextView
        lateinit var month: TextView
        lateinit var year: TextView
        lateinit var itemLayout: ConstraintLayout
        lateinit var dateLayout: LinearLayout

    }

    fun addItemsAtBottom(bottomList: MutableList<HorizontalCalendarItem>) {

        if (bottomList.isEmpty() ) return

        val adapterSize: Int = itemCount
        data.addAll(adapterSize, bottomList)
        notifyItemRangeInserted(adapterSize, adapterSize + bottomList.size)
    }

    fun addItemsAtTop(topList: MutableList<HorizontalCalendarItem>){


        if (topList.isEmpty()) return

        data.addAll(0, topList)
        notifyItemRangeInserted(0, topList.size)
    }



    fun notifyEndReached(){
        val handler: Handler = Handler(Looper.getMainLooper())
        handler.postDelayed( { onEndReachedListener.onEndReached()},50)
    }

    fun notifyStartReached(){
        val handler: Handler = Handler(Looper.getMainLooper())
        handler.postDelayed({ onEndReachedListener.onStartReached()},50)
    }

    fun setBottomAdvanceCallback(bottomAdvance: Int) {
        if (bottomAdvance < 0) {
            throw IndexOutOfBoundsException("Invalid index, bottom index must be greater than 0")
        }

        bottomAdvanceCallback = bottomAdvance
    }

    //this function is used to populate adapter with data
    //It takes a list of Horizontal Calendar Items
    fun setData(data: MutableList<HorizontalCalendarItem>) {
        this.data = data
    }

    interface OnDaySelectedListener {
        fun onDaySelected(view: View, date: String, position: Int)
    }

    interface OnEndReachedListener {
        fun onEndReached()
        fun onStartReached()
    }
}
