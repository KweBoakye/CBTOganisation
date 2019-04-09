package com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HorizontalCalendarUtilsTest{


    @Test
    fun testReturnStringDateWhenBothDayAndMonthNeedALeadingZero(){
        val day = 4
        val month = 6
        val year = 2019
        val stringDate = "04/06/2019"
        val intToStringDate = HorizontalCalendarUtils.returnStringDate(day, month, year)
        assertTrue(stringDate == intToStringDate)
    }

    @Test
    fun testReturnStringDateWhenDayNeedsALeadingZero(){
        val day = 7
        val month = 11
        val year = 2017
        val stringDate = "07/11/2017"
        val intToStringDate = HorizontalCalendarUtils.returnStringDate(day, month, year)
        assertTrue(stringDate == intToStringDate)
    }

    @Test
    fun testReturnStringDateWhenMonthNeedsALeadingZero(){
        val day = 14
        val month = 2
        val year = 2017
        val stringDate = "14/02/2017"
        val intToStringDate = HorizontalCalendarUtils.returnStringDate(day, month, year)
        assertTrue(stringDate == intToStringDate)
    }

    @Test
    fun testReturnStringDateWhenNoVariablesNeedALeadingZero(){
        val day = 14
        val month = 11
        val year = 2017
        val stringDate = "14/11/2017"
        val intToStringDate = HorizontalCalendarUtils.returnStringDate(day, month, year)
        assertTrue(stringDate == intToStringDate)
    }
}