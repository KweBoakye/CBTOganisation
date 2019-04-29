package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager

import android.view.View
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskActivity
import kotlinx.coroutines.Job
import org.threeten.bp.YearMonth

interface MonthViewPagerViewClassInterface {


    interface MonthViewPagerViewClassListener{
        fun setCalendar(month: YearMonth):Job
    }


    fun getRoot(): View
    fun setListener(monthViewPagerViewClassListener: MonthViewPagerViewClassListener)
    fun initViewPager(months: List<YearMonth>,monthIndex: Int)
    fun setToolbar(taskActivity: TaskActivity)

}