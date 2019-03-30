package com.fyp.kweku.cbtoganisation.tasks.presentation.home.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.databinding.HorizontalCalendarItemBinding




class HorizontalCalendarAdapter(private val context: Context,
                                private val onDaySelectedListener: HorizontalCalendarAdapter.OnDaySelectedListener,
                                onEndReachedListener: OnEndReachedListener): RecyclerView.Adapter<HorizontalCalendarAdapter.HorizontalCalendarViewHolder>() {
    private lateinit var data: List<HorizontalCalendarItem>

    lateinit var viewHolderBinding: HorizontalCalendarItemBinding

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): HorizontalCalendarAdapter.HorizontalCalendarViewHolder {
        viewHolderBinding  = DataBindingUtil.inflate( LayoutInflater.from(context), R.layout.horizontal_calendar_item,
            viewGroup,
            false)
        val horizontalCalendarViewHolder = HorizontalCalendarAdapter.HorizontalCalendarViewHolder( viewHolderBinding.root)
        horizontalCalendarViewHolder.day = viewHolderBinding.calendarDay
        horizontalCalendarViewHolder.month = viewHolderBinding.calendarMonth
        horizontalCalendarViewHolder.itemLayout = viewHolderBinding.calendarItem
        return horizontalCalendarViewHolder
    }

    override fun getItemCount(): Int {
        return if (data == null) 0 else data!!.size
    }

    override fun onBindViewHolder(holder: HorizontalCalendarAdapter.HorizontalCalendarViewHolder, position: Int) {
        holder.day.text = data[position].day.toString()
        holder.month.text = HorizontalCalendarUtils.returnMonthName(data[position].month)
        holder.itemLayout.setOnClickListener{ onDaySelectedListener.onDaySelected(it,
            HorizontalCalendarUtils.returnStringDate(data[position].day,data[position].month, data[position].year), position)}

        HorizontalCalendarUtils.configCallLayout(context,holder.itemLayout,data[position].backgroundColor )

    }

    class HorizontalCalendarViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
    lateinit var day: TextView
        lateinit var month: TextView
        lateinit var itemLayout: ConstraintLayout

    }

    fun setData(data: List<HorizontalCalendarItem>) {
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
