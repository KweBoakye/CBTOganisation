package com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar.outerrecyclerview

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.HORIZONTAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar.innerercyclerview.MonthCalendarRecyclerAdapter
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import com.fyp.kweku.cbtoganisation.tasks.presentation.utils.snaponscrolllistener.SnapOnScrollListener
import com.fyp.kweku.cbtoganisation.tasks.presentation.utils.snaponscrolllistener.attachSnapHelperWithListener
import kotlinx.android.synthetic.main.fragment_month_calendar.view.*
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth


class MonthCalendarOuterViewClass(val parent: ViewGroup?, view: View): MonthCalendarOuterViewClassInterface,
    MonthCalendarRecyclerAdapter.OnMonthSelectedListener, MonthCalendarOuterRecyclerAdapter.MonthCalendarListener, MonthCalendarRecyclerAdapter.TaskFetcher{
    override fun onMonthScrolled(listOfDates: MutableList<LocalDate>) {
        sendDatesToListener(listOfDates)
    }


    private  var taskLists: MutableList<MutableList<TaskPresentationModel>> = mutableListOf()

    override fun getTasks(): MutableList<MutableList<TaskPresentationModel>> {
        return taskLists
    }


    override fun getListOfMonths(months: MutableList<YearMonth>): MutableList<MutableList<LocalDate>> {
       return monthCalendarOuterViewClassListener.calculateListOfDatesForEachMonth(months)
    }

    override fun calculateDays(month: YearMonth, position: Int):MutableList<LocalDate> {
      return monthCalendarOuterViewClassListener.tasksForThisMonth(month)
    }



     fun onSnapPositionChange(position: Int) {
       if (monthCalendarOuterRecyclerView.scrollState == RecyclerView.SCROLL_STATE_IDLE){
      outerAdapter.onScrollStopped(position)
       }
    }

fun sendDatesToListener( listOfDates:MutableList<LocalDate>){
    monthCalendarOuterViewClassListener.onMonthSelected(listOfDates)
}

    //private val monthCalendarRecyclerView: RecyclerView = monthCalendarViewClassBinding.monthCalendarOuterRecyclerView
   // private val monthCalendarRecyclerAdapter: MonthCalendarRecyclerAdapter = MonthCalendarRecyclerAdapter()
   // private val childLayoutManager = CustomGridLayoutManager(parent!!.context, 7, GridLayoutManager.VERTICAL, false )
    private val outerLayoutManager = LinearLayoutManager(parent!!.context, HORIZONTAL, false )
    private val outerAdapter = MonthCalendarOuterRecyclerAdapter(this)
    private val monthCalendarOuterRecyclerView: RecyclerView = view.monthCalendarOuterRecyclerView
    private lateinit var monthCalendarOuterViewClassListener: MonthCalendarOuterViewClassInterface.MonthCalendarOuterViewClassListener
    private var snapHelper: SnapHelper = PagerSnapHelperNoFling()





override fun getCurrentMonth(): YearMonth {return outerAdapter.getCurrentMonth()}



    override fun setTaskLists(taskLists: MutableList<MutableList<TaskPresentationModel>>){
       TODO()
       // monthCalendarRecyclerAdapter.setTaskLists(taskLists)
    }

    override fun setChildTaskNamesLists(childTaskLists: MutableList<MutableList<TaskPresentationModel>>){
        //outerAdapter.setChildTaskNamesLists(childTaskLists)
         this.taskLists = childTaskLists
    }



    override fun initMonthCalender() {
        monthCalendarOuterRecyclerView.layoutManager = outerLayoutManager
        monthCalendarOuterRecyclerView.setHasFixedSize(true)
        outerAdapter.setListOfMonthDays()
        monthCalendarOuterRecyclerView.adapter = outerAdapter
       // monthCalendarOuterRecyclerView.attachSnapHelperWithListener(snapHelper,SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL_STATE_IDLE, this)


    }

    override fun setListener(monthCalendarOuterViewClassListener: MonthCalendarOuterViewClassInterface.MonthCalendarOuterViewClassListener) {
        this.monthCalendarOuterViewClassListener = monthCalendarOuterViewClassListener
    }




    //Methods From Implemented Listener Interfaces
    override fun onDaySelected(view: View, date: String, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}