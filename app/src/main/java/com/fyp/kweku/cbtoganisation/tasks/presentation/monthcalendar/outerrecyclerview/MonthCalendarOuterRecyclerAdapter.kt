package com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar.outerrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.databinding.MonthCalendarFrameBinding
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar.innerercyclerview.CustomGridLayoutManager
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar.innerercyclerview.MonthCalendarRecyclerAdapter
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.YearMonthDiffUtil
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar.innerercyclerview.MonthCalendarRecyclerAdapter.TaskFetcher
import com.fyp.kweku.cbtoganisation.tasks.presentation.utils.Divider
import com.xwray.groupie.GroupAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MonthCalendarOuterRecyclerAdapter(val monthCalendarListener: MonthCalendarListener): ListAdapter<YearMonth, MonthCalendarOuterRecyclerAdapter.MonthCalendarOuterViewHolder>(YearMonthDiffUtil()) {

    private var childTaskLists: MutableList<MutableList<TaskPresentationModel>> = mutableListOf()
    private var childTaskNamesLists: MutableList<MutableList<String>> = mutableListOf()
   // private lateinit var childLayoutManager: CustomGridLayoutManager
   //val childAdapter: MonthCalendarRecyclerAdapter = MonthCalendarRecyclerAdapter(monthCalendarListener as TaskFetcher)

    val childAdapter: MonthCalendarRecyclerAdapter = MonthCalendarRecyclerAdapter(monthCalendarListener as TaskFetcher)

    val months: MutableList<YearMonth> =MutableList(1){  (YearMonth.of(2019,3))} //MutableList(24){  (YearMonth.of(2019,1).plusMonths(it.toLong()))}
    //val months: MutableList<YearMonth> = MutableList(2400){  (YearMonth.of(1900,1).plusMonths(it.toLong()))}
    var listofMonthDays:MutableList<MutableList<LocalDate>> = mutableListOf()
    //val months: MutableList<YearMonth> = MutableList(12) { (YearMonth.from(LocalDate.now())).plusMonths(it.toLong())}
    private lateinit var monthCalendarFrameBinding: MonthCalendarFrameBinding
    private val viewPool : RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()

    fun getChildTaskNamesLists():MutableList<MutableList<String>> = this.childTaskNamesLists


    fun setListOfMonthDays(){ listofMonthDays = monthCalendarListener.getListOfMonths(months)}

fun setChildTaskNamesLists(childTaskNamesLists: MutableList<MutableList<String>>){
    this.childTaskNamesLists = childTaskNamesLists
}

fun getCurrentMonth(): YearMonth{ return this.months[0]}

    class MonthCalendarOuterViewHolder(layoutToInflate: View, val monthCalendarOuterRecyclerAdapter: MonthCalendarOuterRecyclerAdapter) : RecyclerView.ViewHolder(layoutToInflate) {
               lateinit var mondayTextView : TextView
        lateinit var tuesdayTextView: TextView
        lateinit var wednesdayTextView: TextView
        lateinit var thursday: TextView
        lateinit var friday: TextView
        lateinit var saturday: TextView
        lateinit var sunday: TextView
        lateinit var monthCalendarRecyclerView: RecyclerView
        lateinit var childLayoutManager: CustomGridLayoutManager

        lateinit var monthYearText : String

        fun initInnerRecyclerView(position: Int,months: MutableList<YearMonth> ,listofMonthDays:MutableList<MutableList<LocalDate>>,  viewPool : RecyclerView.RecycledViewPool) = CoroutineScope(Dispatchers.Main).launch{
            val queue = async(Dispatchers.IO) {
                monthCalendarOuterRecyclerAdapter.monthCalendarListener.getTasks()
            }
            val result: MutableList<MutableList<TaskPresentationModel>> = queue.await()
         bind(position,months,viewPool, result, listofMonthDays )
            }



fun bind(position: Int,months: MutableList<YearMonth> , viewPool : RecyclerView.RecycledViewPool,result: MutableList<MutableList<TaskPresentationModel>> ,listofMonthDays:MutableList<MutableList<LocalDate>>){


    monthCalendarOuterRecyclerAdapter.monthCalendarListener.onMonthScrolled(listofMonthDays[layoutPosition])

    val days = listofMonthDays[position]
    val  childAdapter =  monthCalendarOuterRecyclerAdapter.childAdapter
        childAdapter.setDays(days)
    //childAdapter.setTasks(childTaskNamesLists)
    childLayoutManager =
        CustomGridLayoutManager(
            monthCalendarRecyclerView.context,
            7,
            GridLayoutManager.VERTICAL,
            false
        )

    //childAdapter.setTaskLists(result)


    childLayoutManager.initialPrefetchItemCount = 42
   monthCalendarRecyclerView.layoutManager = childLayoutManager
    monthCalendarRecyclerView.setHasFixedSize(true)
    monthCalendarRecyclerView.adapter = childAdapter
    monthCalendarRecyclerView.setRecycledViewPool(viewPool)

}
    }

fun loader(position: Int){

}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MonthCalendarOuterViewHolder {
        monthCalendarFrameBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.month_calendar_frame, parent, false)
        val monthCalendarOuterViewHolder =
            MonthCalendarOuterViewHolder(
                monthCalendarFrameBinding.root,this
            )
        with(monthCalendarOuterViewHolder){
            mondayTextView   = monthCalendarFrameBinding.textViewMonday
            tuesdayTextView = monthCalendarFrameBinding.textViewTuesday
            wednesdayTextView = monthCalendarFrameBinding.textViewWednesday
            thursday = monthCalendarFrameBinding.textViewThursday
            friday = monthCalendarFrameBinding.textViewFriday
            saturday = monthCalendarFrameBinding.textViewSaturday
            sunday = monthCalendarFrameBinding.textViewSunday
            monthCalendarRecyclerView = monthCalendarFrameBinding.MonthCalendarRecyclerView
        }
        /*monthCalendarOuterViewHolder.monthTitleTextView = monthCalendarFrameBinding.monthTitle
        monthCalendarOuterViewHolder.mondayTextView   = monthCalendarFrameBinding.textViewMonday
        monthCalendarOuterViewHolder.tuesdayTextView = monthCalendarFrameBinding.textViewTuesday
        monthCalendarOuterViewHolder.wednesdayTextView = monthCalendarFrameBinding.textViewWednesday
        monthCalendarOuterViewHolder.thursday = monthCalendarFrameBinding.textViewThursday
        monthCalendarOuterViewHolder.friday = monthCalendarFrameBinding.textViewFriday
        monthCalendarOuterViewHolder.saturday = monthCalendarFrameBinding.textViewSaturday
        monthCalendarOuterViewHolder.sunday = monthCalendarFrameBinding.textViewSunday
        monthCalendarOuterViewHolder.monthCalendarRecyclerView = monthCalendarFrameBinding.MonthCalendarRecyclerView*/

       // val childLayoutManager = CustomGridLayoutManager(parent.context, 7, GridLayoutManager.VERTICAL, false)




        return monthCalendarOuterViewHolder
    }

    override fun onBindViewHolder(holder: MonthCalendarOuterViewHolder, position: Int) {


        holder.initInnerRecyclerView(position, months, listofMonthDays, viewPool)


       /* holder.monthTitleTextView.text = months[position].month.toString()
        val childLayoutManager =
            CustomGridLayoutManager(
                holder.monthCalendarRecyclerView.context,
                7,
                GridLayoutManager.VERTICAL,
                false
            )
        val days = listofMonthDays[position]

        childAdapter.run { setDays(days)}
        //setTaskNamesList(childTaskNamesLists)
        childLayoutManager.initialPrefetchItemCount = 42
        holder.monthCalendarRecyclerView.layoutManager = childLayoutManager
        holder.monthCalendarRecyclerView.setHasFixedSize(true)

        holder.monthCalendarRecyclerView.adapter = childAdapter

        holder.monthCalendarRecyclerView.setRecycledViewPool(viewPool)*/


    }


fun onScrollStopped(layoutPosition: Int){
    //monthCalendarListener.onMonthScrolled(listofMonthDays[layoutPosition])
}


    override fun getItemCount(): Int {
       return months.size
    }

    interface MonthCalendarListener{
        fun calculateDays(month: YearMonth, position: Int):MutableList<LocalDate>
        fun getListOfMonths(months: MutableList<YearMonth>):MutableList<MutableList<LocalDate>>
        fun onMonthScrolled(listOfDates:MutableList<LocalDate>)
        fun getTasks(): MutableList<MutableList<TaskPresentationModel>>
    }

}