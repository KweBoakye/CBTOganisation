package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.viewpager

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.GetTasksInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.HorizontalCalendarProperties
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MonthViewPagerController @Inject constructor(val getTasksInteractorInterface: GetTasksInteractorInterface):
    MonthViewPagerViewClassInterface.MonthViewPagerViewClassListener {


    private lateinit var monthViewPagerViewClassInterface : MonthViewPagerViewClassInterface
    private lateinit var calendarProperties: HorizontalCalendarProperties
    private   var today: LocalDate = LocalDate.now()
    private var currentMonth:YearMonth = YearMonth.from(today)
    private  lateinit var monthOfYear: YearMonth
    private val calendarRange = setCalendarRange()
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    fun bindView(monthViewPagerViewClassInterface : MonthViewPagerViewClassInterface){
        this.monthViewPagerViewClassInterface = monthViewPagerViewClassInterface
        monthViewPagerViewClassInterface.setListener(this)
    }

    fun initViewPager(){
        monthViewPagerViewClassInterface.initViewPager(calendarRange, getCurrentMonthIndex())
    }

    private fun setCalendarRange(): List<YearMonth>{
        return List(2400){  (YearMonth.of(1900,1).plusMonths(it.toLong()))}
    }

     private fun getCurrentMonthIndex():Int{
        return calendarRange.indexOf(currentMonth)
    }

}