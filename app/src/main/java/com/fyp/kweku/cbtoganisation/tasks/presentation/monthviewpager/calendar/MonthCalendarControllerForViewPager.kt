package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.GetTasksInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.MonthCalendarInteractorInterface
import kotlinx.coroutines.*
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MonthCalendarControllerForViewPager @Inject constructor(val getTasksInteractorInterface: GetTasksInteractorInterface,
                                                              val monthCalendarInteractorInterface: MonthCalendarInteractorInterface) {


    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)
    private lateinit var monthCalendarViewClassForViewPagerInterface: MonthCalendarViewClassForViewPagerInterface
    private lateinit var month: YearMonth


    fun setMonth(month: YearMonth) {
        this.month = month
    }

    fun generateCalendarAsync() = scope.async() {
        return@async monthCalendarInteractorInterface.generateCalendarData(month)
    }


    fun setAdapterData(calendarData:Deferred<List<Triple<LocalDate, Boolean, MutableList<String>>>>) = scope.launch(Dispatchers.Main) {
               monthCalendarViewClassForViewPagerInterface.setAdapterData(calendarData.await())
                 }



    fun bindView(monthCalendarViewClassForViewPagerInterface: MonthCalendarViewClassForViewPagerInterface){
        this.monthCalendarViewClassForViewPagerInterface = monthCalendarViewClassForViewPagerInterface

    }

}