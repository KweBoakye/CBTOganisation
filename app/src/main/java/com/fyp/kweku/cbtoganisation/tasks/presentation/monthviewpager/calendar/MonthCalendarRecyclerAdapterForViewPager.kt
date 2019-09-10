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
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.coroutines.*
import org.threeten.bp.LocalDate
import kotlin.coroutines.CoroutineContext

class MonthCalendarRecyclerAdapterForViewPager( val dayListener: DayListener,val dayTextboxBackgroundColor: Int): ListAdapter<Triple<LocalDate, Boolean, MutableList<String>>, MonthCalendarRecyclerAdapterForViewPager.MonthCalendarViewHolder>(
    MonthCalendarDiffUtilItemCallback()
) {



    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)
    private  var height:Int = 0


    private lateinit var monthCalendarCellBinding: MonthCalendarCellBinding



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


        private  fun nullAndOrOutOfBoundsCheckAndReplaceAsync(item: Triple<LocalDate, Boolean, MutableList<String>>, index: Int)=
            scope.async(Dispatchers.Default){
          runCatching { item.third[index] }.getOrDefault("")
        }

        private suspend fun indicateDayIsPartOfMonth(isPartOfMonthBoolean: Boolean, dayTextboxBackgroundColor: Int)= withContext(Dispatchers.Default){
            if (!isPartOfMonthBoolean) monthDayTextView.setBackgroundColor( dayTextboxBackgroundColor)
        }

        private fun setFlexboxLayoutParams(position: Int){
            val layoutParams: ViewGroup.LayoutParams = dayCell.layoutParams

            val flexboxLayoutParams: FlexboxLayoutManager.LayoutParams =
                layoutParams as FlexboxLayoutManager.LayoutParams
            flexboxLayoutParams.maxHeight = height
            flexboxLayoutParams.flexBasisPercent = (1f / 7f)
            flexboxLayoutParams.flexGrow = 1f
            flexboxLayoutParams.flexShrink = 1f
            when (position) {
                0, 7, 14, 21, 28, 35 -> {
                    flexboxLayoutParams.isWrapBefore = true
                }
                else -> {
                    flexboxLayoutParams.isWrapBefore = false
                }
            }
        }

        private suspend fun setText(item: Triple<LocalDate, Boolean, MutableList<String>>){
            monthDayTextView.text =
                item.first.dayOfMonth.toString() //days[position].dayOfMonth.toString()
            indicateDayIsPartOfMonth(item.second, dayTextboxBackgroundColor)
            taskTextView1.text = nullAndOrOutOfBoundsCheckAndReplaceAsync(
                item,
                0
            ).await()//getItem(position)[0].taskName
            taskTextView2.text = nullAndOrOutOfBoundsCheckAndReplaceAsync(item, 1).await()
            taskTextView3.text = nullAndOrOutOfBoundsCheckAndReplaceAsync(item, 2).await()
            taskTextView4.text = nullAndOrOutOfBoundsCheckAndReplaceAsync(item, 3).await()
            taskTextView5.text = nullAndOrOutOfBoundsCheckAndReplaceAsync(item, 4).await()
            dayCell.setOnClickListener { dayListener.daySelected(item.first) }
        }


           suspend fun bind( item: Triple<LocalDate, Boolean, MutableList<String>>,dayTextboxBackgroundColor: Int, position: Int) {
               setFlexboxLayoutParams(position)
              setText(item)
           }
    }

    interface DayListener{
        fun daySelected(date: LocalDate)
    }

}