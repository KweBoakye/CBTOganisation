package com.fyp.kweku.cbtoganisation.tasks.presentation.home.recyclerview.mvc

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.HomeViewClassInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.recyclerview.HorizontalCalendarItem

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