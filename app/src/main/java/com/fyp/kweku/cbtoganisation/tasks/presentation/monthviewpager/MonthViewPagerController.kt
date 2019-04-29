package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager

import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.GetTasksInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.HorizontalCalendarProperties
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class MonthViewPagerController(val getTasksInteractorInterface: GetTasksInteractorInterface): MonthViewPagerViewClassInterface.MonthViewPagerViewClassListener {


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

    fun loadAllTasksForRecycler() = scope.launch(Dispatchers.IO){ getTasksInteractorInterface.sendTasksToPresentationLayer()}

    fun onMonthSelected(month: YearMonth){

    }

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

    override fun setCalendar(month: YearMonth) = filterTasksByMonth(month)

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


    /*fun calculateDates(month: YearMonth):MutableList<LocalDate>{
        val dayOfWeekMonthStartsOn = getDayOfWeekMonthStartsOn(month)
        val numberOfOverflowDaysBeforeMonthStarts = getNumberOfOverflowDaysBeforeMonthStarts(dayOfWeekMonthStartsOn)
        val overflowAsDate = getOverflowAsDate(numberOfOverflowDaysBeforeMonthStarts, month)
        val listOfDates = (getListOfDates(overflowAsDate)).map {
                date ->  LocalDate.parse(date.format(ProjectDateTimeUtils.getCustomDateFormatter()),
            ProjectDateTimeUtils.getCustomDateFormatter() ) }.toMutableList()
        return listOfDates
    }

    override fun calculateListOfDatesForEachMonth(months: MutableList<YearMonth>):MutableList<MutableList<LocalDate>>{
        val listOfDatesForMonths: MutableList<MutableList<LocalDate>> =  mutableListOf()
        months.forEachIndexed { index, month -> listOfDatesForMonths.add( index,calculateDates(month)) }
        return listOfDatesForMonths
    }


    fun getNumberOfOverflowDaysBeforeMonthStarts(dayOfWeek:Int):Int{
        return dayOfWeek-1
    }

    fun getDayOfWeekMonthStartsOn(yearMonth: YearMonth):Int{
        return (yearMonth.atDay(1)).dayOfWeek.value
    }

    fun getListOfDates(date: LocalDate):MutableList<LocalDate>{
        return MutableList(42) {date.plusDays(it.toLong())}
    }

    fun getOverflowAsDate(overFlowDays:Int, currentMonth: YearMonth): LocalDate{
        return if (overFlowDays == 0) currentMonth.atDay(1) else {
            (currentMonth
                .atDay(1))
                .minusDays(overFlowDays.toLong())
        }
    }*/
}