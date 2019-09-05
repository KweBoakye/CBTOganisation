package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TaskOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.TaskRepositoryInterface
import kotlinx.coroutines.*
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MonthCalendarInteractor @Inject constructor(private val taskRepositoryInterface: TaskRepositoryInterface, private val taskOutput: TaskOutput):
    MonthCalendarInteractorInterface {

   var today = LocalDate.now()
    var currentMonth: YearMonth = YearMonth.now()
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    suspend fun getTasksByMonth(startDate: LocalDate, endDate: LocalDate): List<Task>{
        return taskRepositoryInterface.getTaskBy42CalendarMonth(startDate, endDate)
    }

    suspend fun allTasks(): List<Task> = taskRepositoryInterface.getAlltasks()

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

    private fun processTasksv2(tasks: List<Task?>, index: Int, listOfDates:List<LocalDate>):MutableList<String>{

        return tasks.asSequence()
            .filter {
                    taskPresentationModel ->
                ProjectDateTimeUtils.checkIfDateIsInRange(
                    listOfDates[index],
                    taskPresentationModel!!.taskStartDate,
                    taskPresentationModel.taskEndDate
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
           checkisPartOfCurrentMonth(dates[it],month),
           processTasksv2(taskRepositoryInterface
               .getTaskBy42CalendarMonth(dates.first(),dates.last()), it, dates) )}
       Timber.i("$list")
       return@withContext list
    }

    fun  checkisPartOfCurrentMonth(date: LocalDate, month: YearMonth):Boolean{
        return YearMonth.from(date) == month
    }



}