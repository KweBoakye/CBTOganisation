package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.taskbydaydialog

import android.content.Context
import android.view.View
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.threeten.bp.LocalDate

interface TasksBySpecificDayViewClassInterface {

    interface TaskBySpecificDayViewClassListener{

    }

    interface TaskBySpecificDayViewClassFragmentListener{
        fun dismissDialogFragment()

    }

    fun getRoot(): View
    fun setupToolbar()
    fun initRecyclerView(context: Context)
    fun setDate(date: LocalDate?)
    fun setFragmentListener(taskBySpecificDayViewClassFragmentListener: TaskBySpecificDayViewClassFragmentListener)
    fun sendDataToAdapter(tasks: List<TaskPresentationModel>)



}