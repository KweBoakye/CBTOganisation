package com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar

import android.view.View
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.HorizontalCalendarItem
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth

interface MonthCalendarViewClassInterface {

    interface MonthCalendarViewClassListener{
        fun  onDaySelected(date: String)
        fun SmoothScrollToPosionParameters(): Int
        fun CalenderAdapterSetDataParameters(): MutableList<HorizontalCalendarItem>
        fun generateDates():MutableList<LocalDate>
    }

    fun setListener(monthCalendarViewClassListener: MonthCalendarViewClassListener)

    fun getRootView(): View

    fun initMonthCalender()
}