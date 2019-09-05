package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.viewpager

import android.view.View
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskActivity
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.threeten.bp.YearMonth

interface MonthViewPagerViewClassInterface {


    interface MonthViewPagerViewClassListener{

    }


    fun getRoot(): View
    fun setListener(monthViewPagerViewClassListener: MonthViewPagerViewClassListener)
    fun initViewPager(months: List<YearMonth>,monthIndex: Int)
    fun setToolbar(taskActivity: TaskActivity)
    fun setTasks(tasks: List<TaskPresentationModel>)

}