package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TaskOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.TaskRepositoryInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MonthCalendarInteractor @Inject constructor(private val taskRepositoryInterface: TaskRepositoryInterface, private val taskOutput: TaskOutput):
    MonthCalendarInteractorInterface {


    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)



    private fun getDayOfWeekMonthStartsOn(yearMonth: YearMonth):Int{
        return (yearMonth.atDay(1)).dayOfWeek.value
    }

    private fun getNumberOfOverflowDaysBeforeMonthStarts(dayOfWeek:Int):Int{
        return dayOfWeek-1
    }

    private fun getOverflowAsDate(overFlowDays:Int, currentMonth: YearMonth): LocalDate {
        return if (overFlowDays == 0) currentMonth.atDay(1) else {
            (currentMonth
                .atDay(1))
                .minusDays(overFlowDays.toLong())
        }
    }

    private fun processTasks(tasks: List<Task?>, index: Int, listOfDates:List<LocalDate>):MutableList<String>{

        return tasks.asSequence()
            .filter {
                    task ->
                ProjectDateTimeUtils.checkIfDateIsInRange(
                    listOfDates[index],
                    task!!.taskStartDate,
                    task.taskEndDate
                )
            }.map { taskPresentationModel -> taskPresentationModel!!.taskName }
            .toMutableList()
    }


   override suspend fun generateCalendarData(month: YearMonth):List<Triple<LocalDate, Boolean, MutableList<String>>> = withContext(Dispatchers.IO){
        val dayOfWeekMonthStartsOn = getDayOfWeekMonthStartsOn(month)
        val numberOfOverflowDaysBeforeMonthStarts = getNumberOfOverflowDaysBeforeMonthStarts(dayOfWeekMonthStartsOn)
        val overflowAsDate = getOverflowAsDate(numberOfOverflowDaysBeforeMonthStarts, month)
        val dates = List<LocalDate>(42){overflowAsDate.plusDays(it.toLong()) }

       val list = List(42){Triple(dates[it],
           checkIsPartOfCurrentMonth(dates[it],month),
           processTasks(taskRepositoryInterface
               .getTaskBy42CalendarMonth(dates.first(),dates.last()), it, dates) )}
       Timber.i("$list")
       return@withContext list
    }

    private fun  checkIsPartOfCurrentMonth(date: LocalDate, month: YearMonth):Boolean{
        return YearMonth.from(date) == month
    }



}