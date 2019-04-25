package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar

import android.view.View
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.threeten.bp.LocalDate

interface MonthCalendarViewClassForViewPagerInterface {

    interface MonthCalendarViewClass{

    }

    fun getRoot(): View
    fun setAdapterDatesAndTasks(datesAndTasks: List<Pair<LocalDate, MutableList<TaskPresentationModel>>>)
    fun initRecyclerview()
}