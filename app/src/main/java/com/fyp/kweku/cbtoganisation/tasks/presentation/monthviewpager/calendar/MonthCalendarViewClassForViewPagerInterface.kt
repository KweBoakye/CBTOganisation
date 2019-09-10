package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth

interface MonthCalendarViewClassForViewPagerInterface {

    interface MonthCalendarViewClassListener{
        fun setCalendar(month: YearMonth)
    }

    interface MonthCalendarViewClassFragmentListener{
        fun launchDialog(date: LocalDate)
        fun getViewPool(): RecyclerView.RecycledViewPool
    }


    fun getRoot(): View
    fun initRecyclerview()
    fun setAdapterData(datesAndTasks: List<Triple<LocalDate, Boolean, MutableList<String>>>)
    fun setViewPool(viewPool: RecyclerView.RecycledViewPool)

}