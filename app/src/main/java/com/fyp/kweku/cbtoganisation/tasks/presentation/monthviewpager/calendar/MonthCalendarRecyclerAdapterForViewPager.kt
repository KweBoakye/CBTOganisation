package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.databinding.MonthCalendarCellBinding
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.MutableListTaskPresentationModelDiffCallback
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModelDiffCallback
import org.threeten.bp.LocalDate

class MonthCalendarRecyclerAdapterForViewPager( val dayListener: DayListener,val dayTextboxBackgroundColor: Int): RecyclerView.Adapter< MonthCalendarRecyclerAdapterForViewPager.MonthCalendarViewHolder>() {

  // private var datesAndTasks: List<Pair<LocalDate, MutableList<TaskPresentationModel>>> = listOf()

   /* fun setAdapterData(datesAndTasks: List<Pair<LocalDate, MutableList<TaskPresentationModel>>>){
        this.datesAndTasks = datesAndTasks
    }*/



   private var datesAndTasks: List<Triple<LocalDate, Boolean, MutableList<TaskPresentationModel?>>> = listOf()

    fun setDatesAndTasks(datesAndTasks: List<Triple<LocalDate, Boolean, MutableList<TaskPresentationModel?>>>){
        this.datesAndTasks = datesAndTasks
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int= datesAndTasks.size
   lateinit var c: Color

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
        holder.bind(position, dayTextboxBackgroundColor)
    }


    inner class MonthCalendarViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var taskTextView1: TextView
        lateinit var taskTextView2: TextView
        lateinit var taskTextView3: TextView
        lateinit var taskTextView4: TextView
        lateinit var taskTextView5: TextView
        lateinit var monthDayTextView: TextView
        lateinit var dayCell: LinearLayout

        /*fun checkTaskNames(position: Int, index :Int,  taskLists: MutableList<MutableList<TaskPresentationModel>>):String{
            return when (taskLists.isEmpty()){
                true -> {""}
                false ->
                    when (taskLists[position].isEmpty()){
                        true -> {""}
                        false ->
                            when(taskLists[position][index].taskName.isNullOrBlank()){
                                true-> {""}
                                false-> {
                                    taskLists[position][index].taskName
                                }
                            }
                    }
            }
        }*/

        private fun nullAndOrOutOfBoundsCheckAndReplace(position: Int, index: Int):String{
          return  runCatching { datesAndTasks[position].third[index]?.taskName!! }.getOrDefault("")

        }

       /* fun nullChecker(position: Int, index: Int):String{
           return when (datesAndTasks[position].second.isEmpty()) {
                true -> { "" }
               false ->
                   if (index > (datesAndTasks[position].second.size - 1) ) ""
               else
                   when (datesAndTasks[position].second[index]?.taskName == null){
                       true -> ""
                       false -> datesAndTasks[position].second[index]?.taskName!!
                   }

            }
        }*/

        fun indicateDayIsPartOfMonth(isPartOfMonthBoolean: Boolean,dayTextboxBackgroundColor: Int){
            if (isPartOfMonthBoolean) monthDayTextView.setBackgroundColor( dayTextboxBackgroundColor)
        }


        fun bind(position: Int,dayTextboxBackgroundColor: Int) {

            monthDayTextView.text = datesAndTasks[position].first.dayOfMonth.toString() //days[position].dayOfMonth.toString()
            indicateDayIsPartOfMonth(datesAndTasks[position].second,dayTextboxBackgroundColor )
            taskTextView1.text = nullAndOrOutOfBoundsCheckAndReplace(position, 0)//getItem(position)[0].taskName
            taskTextView2.text = nullAndOrOutOfBoundsCheckAndReplace(position, 1)
            taskTextView3.text = nullAndOrOutOfBoundsCheckAndReplace(position, 2)
            taskTextView4.text = nullAndOrOutOfBoundsCheckAndReplace(position, 3)
            taskTextView5.text = nullAndOrOutOfBoundsCheckAndReplace(position, 4)
            dayCell.setOnClickListener { dayListener.daySelected(datesAndTasks[position].first)}
        }
    }

    interface DayListener{
        fun daySelected(date: LocalDate)
    }

}