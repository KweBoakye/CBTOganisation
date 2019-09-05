package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.recyclerview

import android.view.View
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar.MonthCalendarViewClassForViewPagerInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel

interface MonthViewAsRecyclerViewClassInterface {

    interface MonthViewAsRecyclerViewClassInterfaceListener{

    }

    fun getRoot():View
    fun setTasks(tasks: List<TaskPresentationModel>)
    fun initAdapter(fragmentListener: MonthCalendarViewClassForViewPagerInterface.MonthCalendarViewClassFragmentListener)
    fun initRecyclerview()
}