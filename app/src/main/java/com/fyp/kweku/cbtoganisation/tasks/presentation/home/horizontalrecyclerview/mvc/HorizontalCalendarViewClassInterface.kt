package com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.mvc

import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.HorizontalCalendarItem
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.tasksbybydayrecyclerview.TasksByDayRecyclerViewClassInterface

interface HorizontalCalendarViewClassInterface {

    interface HorizontalCalendarViewClassListener{
        fun  onDaySelected(date: String)
        fun onEndReached()
        fun fetchHorizontalCalenderItemMutableListForOnEndReached():MutableList<HorizontalCalendarItem>
        fun onStartReached()
        fun horizontalCalenderItemMutableListForOnStartReached():MutableList<HorizontalCalendarItem>
        fun SmoothScrollToPosionParameters(): Int
        fun CalenderAdapterSetDataParameters():MutableList<HorizontalCalendarItem>
    }


    fun getCalendarRecycler():RecyclerView

    fun setListener(HorizontalCalendarViewClassListener: HorizontalCalendarViewClassInterface.HorizontalCalendarViewClassListener)


    fun initHorizontalCalendar()


}