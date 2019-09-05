package com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask


import android.view.View
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime


interface CreateNewTaskViewClassInterface {



    interface CreateNewTaskListener {
        fun onSaveNewTaskButtonClick(input: Array<String>)
        fun stringValid(string: String): Boolean
    }

    interface FragmentListener{
        fun startDateClicked()
        fun endDateClicked()
        fun startTimeClicked()
        fun endTimeClicked()
    }

    fun getRootView(): View
    fun setListener(listener: CreateNewTaskListener?)
    fun updateStartDate(date: LocalDate)
    fun updateEndDate(date: LocalDate)
    fun updateStartTime(time: LocalTime)
    fun updateEndTime(time:LocalTime)



}