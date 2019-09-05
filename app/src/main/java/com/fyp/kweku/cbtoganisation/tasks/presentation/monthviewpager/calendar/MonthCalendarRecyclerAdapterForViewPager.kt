package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.databinding.MonthCalendarCellBinding
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.diffutilcallbacks.MonthCalendarDiffUtilItemCallback
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.coroutines.*
import org.threeten.bp.LocalDate
import kotlin.coroutines.CoroutineContext

class MonthCalendarRecyclerAdapterForViewPager( val dayListener: DayListener,val dayTextboxBackgroundColor: Int): ListAdapter<Triple<LocalDate, Boolean, MutableList<String>>, MonthCalendarRecyclerAdapterForViewPager.MonthCalendarViewHolder>(
    MonthCalendarDiffUtilItemCallback()
) {

  // private var datesAndTasks: List<Pair<LocalDate, MutableList<TaskPresentationModel>>> = listOf()

   /* fun setAdapterData(datesAndTasks: List<Pair<LocalDate, MutableList<TaskPresentationModel>>>){
        this.datesAndTasks = datesAndTasks
    }*/

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)
    private  var height:Int = 0




   private var datesAndTasks: List<Triple<LocalDate, Boolean, MutableList<TaskPresentationModel?>>> = listOf()

    fun setDatesAndTasks(datesAndTasks: List<Triple<LocalDate, Boolean, MutableList<TaskPresentationModel?>>>){
        this.datesAndTasks = datesAndTasks
        notifyDataSetChanged()
    }

    //override fun getItemCount(): Int= datesAndTasks.size
   //lateinit var c: Color

    private lateinit var monthCalendarCellBinding: MonthCalendarCellBinding

    val DATE: Int = 0
    val TASKPRESENTATIONMODEL: Int = 1

    //private var days: MutableList<LocalDate> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthCalendarViewHolder {
        monthCalendarCellBinding = MonthCalendarCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val monthCalendarViewHolder = MonthCalendarViewHolder(monthCalendarCellBinding.root)
        height= parent.measuredHeight/6

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
        scope.launch(Dispatchers.Main){holder.bind(getItem(position), dayTextboxBackgroundColor, position)}
    }


    inner class MonthCalendarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

        private  fun nullAndOrOutOfBoundsCheckAndReplaceAsync(item: Triple<LocalDate, Boolean, MutableList<String>>, index: Int)= scope.async(Dispatchers.Default){
          runCatching { item.third[index] }.getOrDefault("")
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

        private suspend fun indicateDayIsPartOfMonth(isPartOfMonthBoolean: Boolean, dayTextboxBackgroundColor: Int)= withContext(Dispatchers.Default){

            if (!isPartOfMonthBoolean) monthDayTextView.setBackgroundColor( dayTextboxBackgroundColor)

        }


           suspend fun bind( item: Triple<LocalDate, Boolean, MutableList<String>>,dayTextboxBackgroundColor: Int, position: Int) {
               val layoutParams: ViewGroup.LayoutParams = dayCell.layoutParams
               //val flexboxLayoutParams: FlexboxLayoutManager.LayoutParams = layoutParams as FlexboxLayoutManager.LayoutParams
               val flexboxLayoutParams:FlexboxLayoutManager.LayoutParams = layoutParams as FlexboxLayoutManager.LayoutParams
               flexboxLayoutParams.maxHeight= height
               flexboxLayoutParams.flexBasisPercent = (1f/7f)
               flexboxLayoutParams.flexGrow = 1f
               flexboxLayoutParams.flexShrink = 1f
               when(position) {
                   0,7,14,21,28,35-> {
                       flexboxLayoutParams.isWrapBefore = true}
                   else->{}}





            monthDayTextView.text = item.first.dayOfMonth.toString() //days[position].dayOfMonth.toString()
            indicateDayIsPartOfMonth(item.second,dayTextboxBackgroundColor )
            taskTextView1.text = nullAndOrOutOfBoundsCheckAndReplaceAsync(item, 0).await()//getItem(position)[0].taskName
            taskTextView2.text = nullAndOrOutOfBoundsCheckAndReplaceAsync(item, 1).await()
            taskTextView3.text = nullAndOrOutOfBoundsCheckAndReplaceAsync(item, 2).await()
            taskTextView4.text = nullAndOrOutOfBoundsCheckAndReplaceAsync(item, 3).await()
            taskTextView5.text = nullAndOrOutOfBoundsCheckAndReplaceAsync(item, 4).await()
            dayCell.setOnClickListener { dayListener.daySelected(item.first)}



           /* monthDayTextView.text = datesAndTasks[position].first.dayOfMonth.toString() //days[position].dayOfMonth.toString()
            indicateDayIsPartOfMonth(datesAndTasks[position].second,dayTextboxBackgroundColor )
            taskTextView1.text = nullAndOrOutOfBoundsCheckAndReplace(position, 0)//getItem(position)[0].taskName
            taskTextView2.text = nullAndOrOutOfBoundsCheckAndReplace(position, 1)
            taskTextView3.text = nullAndOrOutOfBoundsCheckAndReplace(position, 2)
            taskTextView4.text = nullAndOrOutOfBoundsCheckAndReplace(position, 3)
            taskTextView5.text = nullAndOrOutOfBoundsCheckAndReplace(position, 4)
            dayCell.setOnClickListener { dayListener.daySelected(datesAndTasks[position].first)}*/
        }
    }

    interface DayListener{
        fun daySelected(date: LocalDate)
    }

}