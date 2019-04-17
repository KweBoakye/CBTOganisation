package com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar

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
import kotlin.coroutines.CoroutineContext

class MonthCalendarController(val getTasksInteractorInterface: GetTasksInteractorInterface): MonthCalendarViewClassInterface.MonthCalendarViewClassListener{
    override fun onDaySelected(date: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    override fun SmoothScrollToPosionParameters(): Int {
        return calendar.dayOfMonth
    }

    override fun CalenderAdapterSetDataParameters(): MutableList<HorizontalCalendarItem> {
        return getCalendarItems(calendarProperties.currentMonth, calendarProperties.currentYear)
    }

    fun initHorizontalCalendar() {
        //set current date to the set time of callendar in millseconds
        setCalenderProperties(calendar.monthValue, calendar.year) // get month and year

        monthCalendarViewClassInterface.initMonthCalender()
    }

    fun setCalenderProperties(currentMonth: Int, currentYear: Int) {
        calendarProperties = HorizontalCalendarProperties(currentMonth, currentYear)
    }

    private lateinit var monthCalendarViewClassInterface: MonthCalendarViewClassInterface
    private lateinit var calendarProperties: HorizontalCalendarProperties
    private var calendar = LocalDate.now()
    private  var monthOfYear: YearMonth = YearMonth.from(calendar)
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    fun prepareTasksForEachDay(listOfDates:MutableList<LocalDate>) = scope.launch(Dispatchers.IO){getTasksInteractorInterface.filterListOfTasksByDay(listOfDates)}

fun getCalendarItems(month: Int, year: Int): MutableList<HorizontalCalendarItem> {
    val items = ArrayList<HorizontalCalendarItem>()
    for (day in 0 until HorizontalCalendarUtils.calculateMonthLength(month, year)) {
        items.add(HorizontalCalendarItem(day + 1, month, R.color.colorPrimary, year))
    }
    return items
}

    //get listofDates
    fun bindView(monthCalendarViewClassInterface: MonthCalendarViewClassInterface){
        this.monthCalendarViewClassInterface = monthCalendarViewClassInterface
    }



    fun setControllerAsHorizontalCalendarViewClassListener(){monthCalendarViewClassInterface.setListener(this)}


   fun getDayOfWeekMonthStartsOn(yearMonth: YearMonth):Int{
       return (yearMonth.atDay(1)).dayOfWeek.value
   }

    fun getListOfDates(date: LocalDate):MutableList<LocalDate>{
        return MutableList(42) {date.plusDays(it.toLong())}
    }

    fun getOverflowAsDate(overFlowDays:Int, currentMonth: YearMonth): LocalDate{
        if (overFlowDays == 0) return  currentMonth.atDay(1)
        else {
        val overflowMonth = currentMonth.minusMonths(1)
        return (overflowMonth.atEndOfMonth().minusDays(overFlowDays.toLong()))}
    }

    override fun generateDates():MutableList<LocalDate>{
        val dayOfWeekMonthStartsOn = getDayOfWeekMonthStartsOn(monthOfYear)
        val numberOfOverflowDaysBeforeMonthStarts = getNumberOfOverflowDaysBeforeMonthStarts(dayOfWeekMonthStartsOn)
        val overflowAsDate = getOverflowAsDate(numberOfOverflowDaysBeforeMonthStarts, monthOfYear)
       val listOfDates = (getListOfDates(overflowAsDate)).map {
                date ->  LocalDate.parse(date.toString(), ProjectDateTimeUtils.getCustomDateFormatter()) }.toMutableList()
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
     return date.month
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



     fun isToday(day: Int,month: Int,year: Int):Boolean{
         return today.dayOfMonth == day && today.monthValue == month && today.year == year
     }*/

}