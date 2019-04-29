package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar

import android.view.View
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth

interface MonthCalendarViewClassForViewPagerInterface {

    interface MonthCalendarViewClassListener{
        fun setCalendar(month: YearMonth)
    }

    interface MonthCalendarViewClassFragmentListener{
        fun launchDialog(date: LocalDate)
    }


    fun getRoot(): View
    fun initRecyclerview()
    fun setAdapterData(datesAndTasks: List<Triple<LocalDate, Boolean, MutableList<TaskPresentationModel?>>>)
    //fun setListener(monthCalendarViewClassListener: MonthCalendarViewClassListener)
    fun setFragmentListener(monthCalendarViewClassFragmentListener: MonthCalendarViewClassFragmentListener)
}