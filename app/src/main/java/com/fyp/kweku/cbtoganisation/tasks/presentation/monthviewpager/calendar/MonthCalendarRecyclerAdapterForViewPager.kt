package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.databinding.MonthCalendarCellBinding
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.MutableListTaskPresentationModelDiffCallback
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModelDiffCallback
import org.threeten.bp.LocalDate

class MonthCalendarRecyclerAdapterForViewPager: RecyclerView.Adapter< MonthCalendarRecyclerAdapterForViewPager.MonthCalendarViewHolder>() {

   private var datesAndTasks: List<Pair<LocalDate, MutableList<TaskPresentationModel>>> = listOf()

    fun setDatesAndTasks(datesAndTasks: List<Pair<LocalDate, MutableList<TaskPresentationModel>>>){
        this.datesAndTasks = datesAndTasks
    }

    override fun getItemCount(): Int= datesAndTasks.size


    private lateinit var monthCalendarCellBinding: MonthCalendarCellBinding

    val DATE: Int = 0
    val TASKPRESENTATIONMODEL: Int = 1

    //private var days: MutableList<LocalDate> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthCalendarViewHolder {
        monthCalendarCellBinding = MonthCalendarCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val monthCalendarViewHolder = MonthCalendarViewHolder(monthCalendarCellBinding.root)
        monthCalendarViewHolder.taskTextView1 = monthCalendarCellBinding.TaskTextView1
        monthCalendarViewHolder.taskTextView2 = monthCalendarCellBinding.TaskTextView2
        monthCalendarViewHolder.taskTextView3 = monthCalendarCellBinding.TaskTextView3
        monthCalendarViewHolder.taskTextView4 = monthCalendarCellBinding.TaskTextView4
        monthCalendarViewHolder.taskTextView5 = monthCalendarCellBinding.TaskTextView5
        monthCalendarViewHolder.dayCell = monthCalendarCellBinding.dayCell
        monthCalendarViewHolder.monthDayTextView = monthCalendarCellBinding.monthDayTextView
        return monthCalendarViewHolder
    }

    override fun onBindViewHolder(holder: MonthCalendarViewHolder, position: Int) {
        holder.bind(position)
    }


    inner class MonthCalendarViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var taskTextView1: TextView
        lateinit var taskTextView2: TextView
        lateinit var taskTextView3: TextView
        lateinit var taskTextView4: TextView
        lateinit var taskTextView5: TextView
        lateinit var monthDayTextView: TextView
        lateinit var dayCell: LinearLayout

        fun bind(position: Int ) {

            monthDayTextView.text = datesAndTasks[position].first.dayOfMonth.toString() //days[position].dayOfMonth.toString()
            taskTextView1.text = if(datesAndTasks[position].second.isEmpty()) "" else datesAndTasks[position].second[0].taskName//getItem(position)[0].taskName
            taskTextView2.text = if(datesAndTasks[position].second.isEmpty()) "" else datesAndTasks[position].second[1].taskName
            taskTextView3.text = if(datesAndTasks[position].second.isEmpty()) "" else datesAndTasks[position].second[2].taskName
            taskTextView4.text = if(datesAndTasks[position].second.isEmpty()) "" else datesAndTasks[position].second[3].taskName
            taskTextView5.text = if(datesAndTasks[position].second.isEmpty()) "" else datesAndTasks[position].second[4].taskName

        }
    }

}