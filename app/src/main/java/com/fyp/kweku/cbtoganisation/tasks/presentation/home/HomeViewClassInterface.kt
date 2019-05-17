package com.fyp.kweku.cbtoganisation.tasks.presentation.home


import android.view.View
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskActivity
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskViewModel
import org.threeten.bp.LocalDate


interface HomeViewClassInterface {

    interface HomeListener{
        fun onGoToCreateNewTaskFragmentButtonClicked(taskActivity: TaskActivity)

    }



    fun getRootView(): View

    fun setToolbar()

    fun setToolbarDate(date: LocalDate)

    fun setListener(listener: HomeListener)

    fun getTaskActivity(): TaskActivity

    fun setTaskActivity(taskActivity: TaskActivity)

    fun setGoToCreateNewTaskFragmentButtonOnClickListener()



    fun bindTaskViewModel(taskViewModel: TaskViewModel)


}