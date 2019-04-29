package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.calendar

import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.GetTasksInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class MonthCalendarControllerForViewPager(val getTasksInteractorInterface: GetTasksInteractorInterface) {


    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)
    private lateinit var monthCalendarViewClassForViewPagerInterface: MonthCalendarViewClassForViewPagerInterface


    private fun calculateDates(month: YearMonth):MutableList<Pair<LocalDate, Boolean>>{
        val dayOfWeekMonthStartsOn = getDayOfWeekMonthStartsOn(month)
        val numberOfOverflowDaysBeforeMonthStarts = getNumberOfOverflowDaysBeforeMonthStarts(dayOfWeekMonthStartsOn)
        val overflowAsDate = getOverflowAsDate(numberOfOverflowDaysBeforeMonthStarts, month)
        Timber.i("$overflowAsDate")
        val listOfDates = (calculatesDatesAndBoolean(overflowAsDate,month)).map {
                date -> Pair(LocalDate.parse(date.first.format(ProjectDateTimeUtils.getCustomDateFormatter()),
            ProjectDateTimeUtils.getCustomDateFormatter() ), date.second) }.toMutableList()
        return listOfDates
    }

    fun bindView(monthCalendarViewClassForViewPagerInterface: MonthCalendarViewClassForViewPagerInterface){
        this.monthCalendarViewClassForViewPagerInterface = monthCalendarViewClassForViewPagerInterface
    }

  fun initRecyclerview(datesAndTasks: List<Triple<LocalDate, Boolean, MutableList<TaskPresentationModel?>>>){

  }

    fun loadAllTasksForRecycler() = scope.launch(Dispatchers.IO){ getTasksInteractorInterface.sendTasksToPresentationLayer()}
    fun filterTasksByMonth(month: YearMonth) = scope.launch(Dispatchers.IO){ getTasksInteractorInterface.filterTasksByMonth(calculateDates(month))}
    fun getDatesAndTasksByMonthAsAny() = getTasksInteractorInterface.getDatesAndTasksByMonthAsAny()
    private fun getNumberOfOverflowDaysBeforeMonthStarts(dayOfWeek:Int):Int{
        return dayOfWeek-1
    }

   fun  checkisPartOfCurrentMonth(date: LocalDate, month: YearMonth):Boolean{
       return YearMonth.from(date) == month
   }

    private fun getDayOfWeekMonthStartsOn(yearMonth: YearMonth):Int{
        return (yearMonth.atDay(1)).dayOfWeek.value
    }

    fun calculatesDatesAndBoolean(date: LocalDate, month: YearMonth): MutableList<Pair<LocalDate, Boolean>>{
        return MutableList(42){ Pair(date.plusDays(it.toLong()), checkisPartOfCurrentMonth(date.plusDays(it.toLong()), month))}
    }

    private fun getListOfDates(date: LocalDate):MutableList<LocalDate>{
        return MutableList(42) {date.plusDays(it.toLong())}
    }

    private fun getOverflowAsDate(overFlowDays:Int, currentMonth: YearMonth): LocalDate {
        return if (overFlowDays == 0) currentMonth.atDay(1) else {
            (currentMonth
                .atDay(1))
                .minusDays(overFlowDays.toLong())
        }
    }




    // MayNotNeed


}