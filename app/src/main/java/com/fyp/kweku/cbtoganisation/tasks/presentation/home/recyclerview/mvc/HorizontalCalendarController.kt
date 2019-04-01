package com.fyp.kweku.cbtoganisation.tasks.presentation.home.recyclerview.mvc


import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.recyclerview.HorizontalCalendarItem
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.recyclerview.HorizontalCalendarProperties
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.recyclerview.HorizontalCalendarUtils
import org.threeten.bp.LocalDate



class HorizontalCalendarController: HorizontalCalendarViewClassInterface.HorizontalCalendarViewClassListener {

    private lateinit var horizontalCalendarViewClassInterface: HorizontalCalendarViewClassInterface
    private lateinit var calendarProperties: HorizontalCalendarProperties
    private var calendar = LocalDate.now() // get instacne of calendar

    override fun onDaySelected(date: String){
        updateEntries(date)
    }

    override fun onEndReached() {
        val monthAtEnd: Int = calendarProperties.monthAtEnd
        if (monthAtEnd == 0) {
            calendarProperties.monthAtEnd = 11
        } else {
            calendarProperties.monthAtEnd = monthAtEnd - 1
        }

    }

     override fun fetchHorizontalCalenderItemMutableListForOnEndReached():MutableList<HorizontalCalendarItem>{
        return getCalendarItems(
             calendarProperties.monthAtEnd,
             calendarProperties.currentYear
         )
     }

    override fun onStartReached(){
        val monthAtStart: Int = calendarProperties.monthAtStart
        if (monthAtStart == 11) {
            calendarProperties.monthAtStart = 0
        } else {
            calendarProperties.monthAtStart = monthAtStart + 1
        }
    }

    override fun horizontalCalenderItemMutableListForOnStartReached():MutableList<HorizontalCalendarItem>{
        return getCalendarItems( calendarProperties.monthAtStart, calendarProperties.currentYear)
    }

    override fun SmoothScrollToPosionParameters(): Int{
        return calendar.dayOfMonth // day of month as Integer
    }

    fun bindView(horizontalCalendarViewClassInterface: HorizontalCalendarViewClassInterface){
       this.horizontalCalendarViewClassInterface = horizontalCalendarViewClassInterface
    }

    override fun CalenderAdapterSetDataParameters(): MutableList<HorizontalCalendarItem> {
        return getCalendarItems(calendarProperties.currentMonth, calendarProperties.currentYear)
    }

    fun initHorizontalCalendar() {
         //set current date to the set time of callendar in millseconds
        setCalenderProperties(calendar.monthValue, calendar.year) // get month and year

       horizontalCalendarViewClassInterface.initHorizontalCalendar()
    }

    fun getCalendarItems(month: Int, year: Int): MutableList<HorizontalCalendarItem> {
        val items = ArrayList<HorizontalCalendarItem>()
        for (day in 0 until HorizontalCalendarUtils.calculateMonthLength(month)) {
            items.add(HorizontalCalendarItem(day + 1, month, R.color.colorPrimary, year))
        }
        return items
    }

    fun setCalenderProperties(currentMonth: Int, currentYear: Int) {
        calendarProperties = HorizontalCalendarProperties(currentMonth, currentYear)
    }

     fun updateEntries(date: String) {

    }


    fun setControllerAsHorizontalCalendarViewClassListener(){horizontalCalendarViewClassInterface.setListener(this)}
}