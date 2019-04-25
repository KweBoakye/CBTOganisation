package com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar.innerercyclerview

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
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.MutableListTaskPresentationModelDiffCallback
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModelDiffCallback
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import org.threeten.bp.LocalDate

import timber.log.Timber


class MonthCalendarRecyclerAdapter(val taskFetcher: TaskFetcher): RecyclerView.Adapter<MonthCalendarRecyclerAdapter.MonthCalendarViewHolder>() {

   // private  var data2: MutableList<MutableList<TaskPresentationModel>> = mutableListOf(mutableListOf<TaskPresentationModel>())
   // private var days:MutableList<LocalDate> = mutableListOf()
    private var days:MutableList<LocalDate> = mutableListOf()//MutableList<Int>(42){(1..42).step}

    private var taskLists: MutableList<MutableList<TaskPresentationModel>> = mutableListOf()

    var taskNamesLists: MutableList<MutableList<String>> = mutableListOf()

    private lateinit var  monthCalendarBinding: MonthCalendarCellBinding

    inner class MonthCalendarViewHolder(itemView: View, val monthCalendarRecyclerAdapter: MonthCalendarRecyclerAdapter):RecyclerView.ViewHolder(itemView){
        lateinit var monthDayTextView:TextView
        lateinit var taskTextView1: TextView
        lateinit var taskTextView2:TextView
        lateinit var taskTextView3:TextView
        lateinit var taskTextView4:TextView
        lateinit var taskTextView5: TextView
        lateinit var dayCell: LinearLayout


       /* fun retrieveTasks(position: Int, taskList: MutableList<TaskPresentationModel>)= CoroutineScope(Dispatchers.Main).launch{
            val queue = async(Dispatchers.IO) {
                taskFetcher.getTasks()
            }
            val result: MutableList<MutableList<TaskPresentationModel>>? = queue.await()
            monthCalendarRecyclerAdapter.submitList(result)
        bind(position, taskList)}*/

        fun bind(position: Int, taskLists: MutableList<MutableList<TaskPresentationModel>>){
            monthDayTextView.text = days[position].dayOfMonth.toString()
            taskTextView1.text      = checkTaskNames(position,0,taskLists)
            taskTextView2.text      = checkTaskNames(position,1,taskLists)
            taskTextView3.text      = checkTaskNames(position,2,taskLists)
            taskTextView4.text      = checkTaskNames(position,3,taskLists)
            taskTextView5.text      = checkTaskNames(position,4,taskLists)
        }

    }

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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthCalendarViewHolder {
        monthCalendarBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.month_calendar_cell, parent, false)

        val monthCalendarViewHolder = MonthCalendarViewHolder(monthCalendarBinding.root, this)
        monthCalendarViewHolder.monthDayTextView =monthCalendarBinding.monthDayTextView
        monthCalendarViewHolder.taskTextView1=monthCalendarBinding.TaskTextView1
        monthCalendarViewHolder.taskTextView2=monthCalendarBinding.TaskTextView2
        monthCalendarViewHolder.taskTextView3=monthCalendarBinding.TaskTextView3
        monthCalendarViewHolder.taskTextView4=monthCalendarBinding.TaskTextView4
        monthCalendarViewHolder.taskTextView5=monthCalendarBinding.TaskTextView5
        monthCalendarViewHolder.dayCell=monthCalendarBinding.dayCell

        return monthCalendarViewHolder
    }

    override fun getItemCount(): Int = days.size


    fun setDays(days:MutableList<LocalDate>){
        this.days = days
    }

    fun setTasks(childTaskNamesLists: MutableList<MutableList<String>>){
        taskNamesLists = childTaskNamesLists
    }

    fun setTaskNamesList(taskNamesLists: MutableList<MutableList<String>>){
        this.taskNamesLists = taskNamesLists
        notifyDataSetChanged()
    }


   fun setTaskLists(taskLists: MutableList<MutableList<TaskPresentationModel>>){
       this.taskLists  = taskLists
       notifyDataSetChanged()
   }

    fun taskNamesListsChecker(position: Int, index :Int): String{
        //Timber.i(taskNamesLists[position][index])
        return if (taskNamesLists.isEmpty()) ""
        else taskNamesLists[position][index]



    }

    override fun onBindViewHolder(holder: MonthCalendarViewHolder, position: Int) {
        //Timber.i("$childTaskNamesLists")
        //holder.monthDayTextView.text = days[position].dayOfMonth.toString()
       // taskNamesLists = taskFetcher.getTasks()
       /* holder.monthDayTextView.text = days[position].dayOfMonth.toString()
        holder.taskTextView1.text = taskNamesListsChecker(position,0)*/

        holder.bind(position, taskLists)

        /* val dayTaskList: MutableList<String> = childTaskNamesLists[position]
         holder.taskTextView1.text = *//*taskLists == null||*//* dayTaskList[0]
        holder.taskTextView2.text = if (*//*taskLists== null||*//* childTaskNamesLists.isEmpty()) "" else childTaskNamesLists[position][1]
        holder.taskTextView3.text = if (*//*taskLists== null||*//* childTaskNamesLists.isEmpty()) "" else childTaskNamesLists[position][2]
        holder.taskTextView4.text = if (*//*taskLists== null||*//* childTaskNamesLists.isEmpty()) "" else childTaskNamesLists[position][3]
        holder.taskTextView5.text = if (*//*taskLists== null||*//* childTaskNamesLists.isEmpty()) "" else childTaskNamesLists[position][4]*/

    }




    interface OnMonthSelectedListener {
        fun onDaySelected(view: View, date: String, position: Int)
    }

    interface TaskFetcher{
        fun getTasks(): MutableList<MutableList<TaskPresentationModel>>
    }


}


/*
package com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar.innerercyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.databinding.MonthCalendarCellBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import timber.log.Timber


class MonthCalendarRecyclerAdapter(val taskFetcher: TaskFetcher): RecyclerView.Adapter<MonthCalendarRecyclerAdapter.MonthCalendarViewHolder>() {

    // private  var data2: MutableList<MutableList<TaskPresentationModel>> = mutableListOf(mutableListOf<TaskPresentationModel>())
    // private var days:MutableList<LocalDate> = mutableListOf()
    private var days:MutableList<LocalDate> = mutableListOf()//MutableList<Int>(42){(1..42).step}

    // private var taskLists: MutableList<MutableList<TaskPresentationModel>> = mutableListOf()

    var taskNamesLists: MutableList<MutableList<String>> = mutableListOf()

    private lateinit var  monthCalendarBinding: MonthCalendarCellBinding

    inner class MonthCalendarViewHolder(itemView: View, val monthCalendarRecyclerAdapter: MonthCalendarRecyclerAdapter):RecyclerView.ViewHolder(itemView){
        lateinit var monthDayTextView:TextView
        lateinit var taskTextView1: TextView
        lateinit var taskTextView2:TextView
        lateinit var taskTextView3:TextView
        lateinit var taskTextView4:TextView
        lateinit var taskTextView5: TextView
        lateinit var dayCell: LinearLayout

        fun bind(position: Int)= CoroutineScope(Dispatchers.Main).launch{
            val queue = async(Dispatchers.IO) {
                taskFetcher.getTasks()
            }
            val result = queue.await()
            monthCalendarRecyclerAdapter.setTaskNamesList(result)
            monthDayTextView.text = days[position].dayOfMonth.toString()
            taskTextView1.text      = taskNamesListsChecker(position,0)
            taskTextView2.text      = taskNamesListsChecker(position,1)
            taskTextView3.text      = taskNamesListsChecker(position,2)
            taskTextView4.text      = taskNamesListsChecker(position,3)
            taskTextView5.text      = taskNamesListsChecker(position,4)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthCalendarViewHolder {
        monthCalendarBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.month_calendar_cell, parent, false)

        val monthCalendarViewHolder = MonthCalendarViewHolder(monthCalendarBinding.root, this)
        monthCalendarViewHolder.monthDayTextView =monthCalendarBinding.monthDayTextView
        monthCalendarViewHolder.taskTextView1=monthCalendarBinding.TaskTextView1
        monthCalendarViewHolder.taskTextView2=monthCalendarBinding.TaskTextView2
        monthCalendarViewHolder.taskTextView3=monthCalendarBinding.TaskTextView3
        monthCalendarViewHolder.taskTextView4=monthCalendarBinding.TaskTextView4
        monthCalendarViewHolder.taskTextView5=monthCalendarBinding.TaskTextView5
        monthCalendarViewHolder.dayCell=monthCalendarBinding.dayCell

        return monthCalendarViewHolder
    }

    override fun getItemCount(): Int = days.size


    fun setDays(days:MutableList<LocalDate>){
        this.days = days
    }

    fun setTasks(childTaskNamesLists: MutableList<MutableList<String>>){
        taskNamesLists = childTaskNamesLists
    }

    fun setTaskNamesList(taskNamesLists: MutableList<MutableList<String>>){
        this.taskNamesLists = taskNamesLists
        notifyDataSetChanged()
    }


    */
/* fun setTaskLists(taskLists: MutableList<MutableList<TaskPresentationModel>>){
         this.taskLists  = taskLists
     }*//*


    fun taskNamesListsChecker(position: Int, index :Int): String{
        //Timber.i(taskNamesLists[position][index])
        return if (taskNamesLists.isEmpty()) ""
        else taskNamesLists[position][index]



    }

    override fun onBindViewHolder(holder: MonthCalendarViewHolder, position: Int) {
        //Timber.i("$childTaskNamesLists")
        //holder.monthDayTextView.text = days[position].dayOfMonth.toString()
        // taskNamesLists = taskFetcher.getTasks()
        */
/* holder.monthDayTextView.text = days[position].dayOfMonth.toString()
         holder.taskTextView1.text = taskNamesListsChecker(position,0)*//*


        holder.bind(position)
        */
/* val dayTaskList: MutableList<String> = childTaskNamesLists[position]
         holder.taskTextView1.text = *//*
*/
/*taskLists == null||*//*
*/
/* dayTaskList[0]
        holder.taskTextView2.text = if (*//*
*/
/*taskLists== null||*//*
*/
/* childTaskNamesLists.isEmpty()) "" else childTaskNamesLists[position][1]
        holder.taskTextView3.text = if (*//*
*/
/*taskLists== null||*//*
*/
/* childTaskNamesLists.isEmpty()) "" else childTaskNamesLists[position][2]
        holder.taskTextView4.text = if (*//*
*/
/*taskLists== null||*//*
*/
/* childTaskNamesLists.isEmpty()) "" else childTaskNamesLists[position][3]
        holder.taskTextView5.text = if (*//*
*/
/*taskLists== null||*//*
*/
/* childTaskNamesLists.isEmpty()) "" else childTaskNamesLists[position][4]*//*


    }




    interface OnMonthSelectedListener {
        fun onDaySelected(view: View, date: String, position: Int)
    }

    interface TaskFetcher{
        fun getTasks(): MutableList<MutableList<String>>
    }



}*/