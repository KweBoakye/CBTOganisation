package com.fyp.kweku.cbtoganisation.tasks.presentation.groupiemonthcalendar.extensions

import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.month_calendar_cell.*
import org.threeten.bp.LocalDate

class DayItem(val days: MutableList<LocalDate>, val taskLists: MutableList<MutableList<TaskPresentationModel>>)  : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.monthDayTextView.text = days[position].dayOfMonth.toString()
        viewHolder.TaskTextView1.text = checkTaskNames(position,0,taskLists)
        viewHolder.TaskTextView2.text  = checkTaskNames(position,1,taskLists)
        viewHolder.TaskTextView3.text = checkTaskNames(position,2,taskLists)
        viewHolder.TaskTextView4.text = checkTaskNames(position,3,taskLists)
        viewHolder.TaskTextView5.text = checkTaskNames(position,4,taskLists)

    }

    override fun getLayout(): Int = R.layout.month_calendar_cell


    fun checkTaskNames(position: Int, index :Int,  taskLists: MutableList<MutableList<TaskPresentationModel>>):String{
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
    }
}