package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar

import android.view.View
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.threeten.bp.LocalDate

interface MonthCalendarViewClassForViewPagerInterface {

    interface MonthCalendarViewClassListener{

    }

    interface MonthCalendarViewClassFragmentListener{
        fun launchDialog(date: LocalDate)
    }


    fun getRoot(): View
    fun initRecyclerview(datesAndTasks: List<Triple<LocalDate, Boolean, MutableList<TaskPresentationModel?>>>)
    //fun setListener()
    fun setFragmentListener(monthCalendarViewClassFragmentListener: MonthCalendarViewClassFragmentListener)
}