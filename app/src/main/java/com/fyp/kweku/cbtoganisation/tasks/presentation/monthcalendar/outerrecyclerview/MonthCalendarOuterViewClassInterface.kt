package com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar.outerrecyclerview

import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.HorizontalCalendarItem
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth

interface MonthCalendarOuterViewClassInterface {

    interface MonthCalendarOuterViewClassListener{

        fun smoothScrollToPositionParameters(): Int
        fun tasksForThisMonth(month: YearMonth):MutableList<LocalDate>
        fun calculateListOfDatesForEachMonth(months: MutableList<YearMonth>):MutableList<MutableList<LocalDate>>
        fun onMonthSelected(listOfDates:MutableList<LocalDate>)
    }

    fun setListener(monthCalendarOuterViewClassListener: MonthCalendarOuterViewClassListener)


    fun initMonthCalender()


    fun setTaskLists(taskLists: MutableList<MutableList<TaskPresentationModel>>)

    fun setChildTaskNamesLists(childTaskLists: MutableList<MutableList<TaskPresentationModel>>)

    fun getCurrentMonth(): YearMonth
}