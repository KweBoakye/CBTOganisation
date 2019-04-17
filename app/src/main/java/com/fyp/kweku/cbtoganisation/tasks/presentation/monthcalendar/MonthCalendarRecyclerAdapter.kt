package com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.databinding.MonthCalendarCellBinding
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.HorizontalCalendarItem
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.HorizontalCalendarItemDiffCallback
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.threeten.bp.LocalDate

class MonthCalendarRecyclerAdapter(val onDaySelectedListener: OnDaySelectedListener): RecyclerView.Adapter<MonthCalendarRecyclerAdapter.MonthCalendarViewHolder>() {

   // private  var data2: MutableList<MutableList<TaskPresentationModel>> = mutableListOf(mutableListOf<TaskPresentationModel>())
    private var days:MutableList<LocalDate> = mutableListOf()

    private  var tasklists: MutableList<MutableList<TaskPresentationModel>>? = null

private lateinit var data: MutableList<HorizontalCalendarItem>


    private lateinit var  monthCalendarBinding: MonthCalendarCellBinding
    inner class MonthCalendarViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        lateinit var monthDayTextView:TextView
        lateinit var taskTextView1: TextView
        lateinit var taskTextView2:TextView
        lateinit var taskTextView3:TextView
        lateinit var taskTextView4:TextView
        lateinit var taskTextView5: TextView
        lateinit var dayCell: LinearLayout

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthCalendarViewHolder {
        monthCalendarBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.month_calendar_cell, parent, false)

        val monthCalendarViewHolder = MonthCalendarViewHolder(monthCalendarBinding.root)
        monthCalendarViewHolder.monthDayTextView =monthCalendarBinding.monthDayTextView
        monthCalendarViewHolder.taskTextView1=monthCalendarBinding.TaskTextView1
        monthCalendarViewHolder.taskTextView2=monthCalendarBinding.TaskTextView2
        monthCalendarViewHolder.taskTextView3=monthCalendarBinding.TaskTextView3
        monthCalendarViewHolder.taskTextView4=monthCalendarBinding.TaskTextView4
        monthCalendarViewHolder.taskTextView5=monthCalendarBinding.TaskTextView5
        monthCalendarViewHolder.dayCell=monthCalendarBinding.dayCell


        return monthCalendarViewHolder
    }

    override fun getItemCount(): Int {
        return days.size
    }

    fun setDays(days:MutableList<LocalDate>){
        this.days = days
    }

    fun setData(data: MutableList<HorizontalCalendarItem>){
        this.data = data
    }

    override fun onBindViewHolder(holder: MonthCalendarViewHolder, position: Int) {
        holder.monthDayTextView.text = days[position].dayOfMonth.toString()
        holder.taskTextView1.text = if (tasklists == null) "" else tasklists!![position][1].taskName
        holder.taskTextView2.text = if (tasklists== null) "" else tasklists!![position][2].taskName
        holder.taskTextView3.text = if (tasklists== null) "" else tasklists!![position][3].taskName
        holder.taskTextView4.text = if (tasklists== null) "" else tasklists!![position][4].taskName
        holder.taskTextView5.text = if (tasklists== null) "" else tasklists!![position][5].taskName
    }


    interface OnDaySelectedListener {
        fun onDaySelected(view: View, date: String, position: Int)
    }


}