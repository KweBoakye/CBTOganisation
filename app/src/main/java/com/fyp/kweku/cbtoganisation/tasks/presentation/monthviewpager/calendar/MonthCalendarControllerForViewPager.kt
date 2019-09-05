package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar

import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.GetTasksInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.MonthCalendarInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.diffutilcallbacks.MonthViewDay
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
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
    private  var calendarData: Deferred<List<Triple<LocalDate, Boolean, MutableList<String>>>> = CompletableDeferred()
    private var isViewBound: Boolean = false


    fun setMonth(month: YearMonth){
        this.month = month
    }

    fun generateCalendar() = scope.launch {
        monthCalendarViewClassForViewPagerInterface.setAdapterData(monthCalendarInteractorInterface.generateCalendarData(month)) }  //scope.launch(Dispatchers.IO){calendarData = async { monthCalendarInteractorInterface.generateCalendarData(month)}}


       fun setAdapterData() = scope.launch(Dispatchers.IO) {
           val calendarDataRecieved = calendarData.await()
           withContext(Dispatchers.Main) {
               monthCalendarViewClassForViewPagerInterface.setAdapterData(calendarDataRecieved)

                 }
           }


    fun bindView(monthCalendarViewClassForViewPagerInterface: MonthCalendarViewClassForViewPagerInterface){
        this.monthCalendarViewClassForViewPagerInterface = monthCalendarViewClassForViewPagerInterface
        isViewBound = true
    }




    private fun getNumberOfOverflowDaysBeforeMonthStarts(dayOfWeek:Int):Int{
        return dayOfWeek-1
    }

   fun  checkisPartOfCurrentMonth(date: LocalDate, month: YearMonth):Boolean{
       return YearMonth.from(date) == month
   }

    private fun getDayOfWeekMonthStartsOn(yearMonth: YearMonth):Int{
        return (yearMonth.atDay(1)).dayOfWeek.value
    }



    private fun getOverflowAsDate(overFlowDays:Int, currentMonth: YearMonth): LocalDate {
        return if (overFlowDays == 0) currentMonth.atDay(1) else {
            (currentMonth
                .atDay(1))
                .minusDays(overFlowDays.toLong())
        }
    }






}