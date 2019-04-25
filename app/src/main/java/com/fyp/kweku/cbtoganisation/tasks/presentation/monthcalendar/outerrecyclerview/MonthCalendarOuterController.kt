package com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar.outerrecyclerview

import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.GetTasksInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.HorizontalCalendarItem
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.HorizontalCalendarProperties
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview.HorizontalCalendarUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class MonthCalendarOuterController(val getTasksInteractorInterface: GetTasksInteractorInterface):
    MonthCalendarOuterViewClassInterface.MonthCalendarOuterViewClassListener {


    override fun onMonthSelected(listOfDates:MutableList<LocalDate>){
        prepareTasksForEachDay(listOfDates)
    }



    override fun smoothScrollToPositionParameters(): Int {
        return calendar.monthValue
    }


    fun initMonthCalender() {
        monthCalendarOuterViewClassInterface.initMonthCalender()

    }

    fun setCalenderProperties(currentMonth: Int, currentYear: Int) {
        calendarProperties = HorizontalCalendarProperties(currentMonth, currentYear)
    }

    private lateinit var monthCalendarOuterViewClassInterface: MonthCalendarOuterViewClassInterface
    private lateinit var calendarProperties: HorizontalCalendarProperties
    private   var calendar: LocalDate = LocalDate.now()
    private  lateinit var monthOfYear: YearMonth
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    fun calculateYearMonth(calendar: LocalDate):YearMonth{return YearMonth.from(calendar)}

    fun prepareTasksForEachDay(listOfDates:MutableList<LocalDate>) = scope.launch(Dispatchers.IO){getTasksInteractorInterface.filterListOfTasksByDay(listOfDates)}

    fun loadAllTasksForRecycler() = scope.launch(Dispatchers.IO){ getTasksInteractorInterface.sendTasksToPresentationLayer()}

fun setCalendarRange(): List<YearMonth>{
    return MutableList(2400){  (YearMonth.of(1900,1).plusMonths(it.toLong()))}
}

    //get listofDates
    fun bindView(monthCalendarOuterViewClassInterface: MonthCalendarOuterViewClassInterface){
        this.monthCalendarOuterViewClassInterface = monthCalendarOuterViewClassInterface
    }



    fun setControllerAsMonthCalendarViewClassListener(){monthCalendarOuterViewClassInterface.setListener(this)
    Timber.i("Listener set")}


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
    }

   override fun calculateListOfDatesForEachMonth(months: MutableList<YearMonth>):MutableList<MutableList<LocalDate>>{
        val listOfDatesForMonths: MutableList<MutableList<LocalDate>> =  mutableListOf()
            months.forEachIndexed { index, month -> listOfDatesForMonths.add( index,calculateDates(month)) }
        return listOfDatesForMonths
    }

    fun calculateDates(month: YearMonth):MutableList<LocalDate>{
        val dayOfWeekMonthStartsOn = getDayOfWeekMonthStartsOn(month)
        val numberOfOverflowDaysBeforeMonthStarts = getNumberOfOverflowDaysBeforeMonthStarts(dayOfWeekMonthStartsOn)
        val overflowAsDate = getOverflowAsDate(numberOfOverflowDaysBeforeMonthStarts, month)
        val listOfDates = (getListOfDates(overflowAsDate)).map {
                date ->  LocalDate.parse(date.format(ProjectDateTimeUtils.getCustomDateFormatter()),ProjectDateTimeUtils.getCustomDateFormatter() ) }.toMutableList()
        return listOfDates
    }

    override fun tasksForThisMonth(month: YearMonth):MutableList<LocalDate>{

        //monthOfYear = calculateYearMonth(calendar)

       val listOfDates = calculateDates(month)
        Timber.i("$listOfDates")
        prepareTasksForEachDay(listOfDates)

        return listOfDates
    }

    /*fun getdayOfWeekMonthStartsOn(date: LocalDate): DayOfWeek {
      val firstDay =date.withDayOfMonth(1)
        return firstDay.dayOfWeek
    }*/

    fun getNumberOfOverflowDaysBeforeMonthStarts(dayOfWeek:Int):Int{
        return dayOfWeek-1
    }

    fun getDayOfWeekAsInt(dayOfWeek: DayOfWeek):Int{
        return dayOfWeek.value
    }

    /*


     fun getMonthLength(date: LocalDate):Int{
        return date.lengthOfMonth()
     }

 fun getMonthNameFromLocalDate(date:LocalDate):Month{
     return date.months
 }

     fun getMonthLengthFromLocalDateAsInt(date: LocalDate):Int{
         return date.lengthOfMonth()
     }

     fun getDayOfWeekEnum(date: LocalDate):DayOfWeek{
         return date.dayOfWeek
     }






     fun nextMonth(): Month{
        return currentMonth.plus(1)
     }

     fun previousMonth(): Month{
     return currentMonth.minus(1)
     }

     fun getPreMonthStartDates(){

     }

 private fun populateMonth(){

 }



     fun isToday(day: Int,months: Int,year: Int):Boolean{
         return today.dayOfMonth == day && today.monthValue == months && today.year == year
     }*/

}