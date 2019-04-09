package com.fyp.kweku.cbtoganisation.common

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.Assertions.assertTrue
import org.threeten.bp.LocalDate

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectDateTimeUtilsTest {

val dateBetween= LocalDate.parse("2016-04-20")
    val dateBeforeButNotAfter = LocalDate.parse("2016-03-15")
    val dateAfterButNotBefore = LocalDate.parse("2016-06-20")
    val startDate = LocalDate.parse("2016-04-15")
    val endDate = LocalDate.parse("2016-05-15")



    @Test
    fun testGetCustomDateFormatter() {
    val formattedUsingDefault = LocalDate.parse("2018-04-20")
    val formattedUsingCustom = LocalDate.parse("20/04/2018", ProjectDateTimeUtils.getCustomDateFormatter())
        assert(formattedUsingCustom == formattedUsingDefault)
    }



    @Test
    fun testCheckIfDateIsInRangeReturnsTrue(){
       assertTrue(ProjectDateTimeUtils.checkIfDateIsInRange(dateBetween,startDate,endDate))
    }

    @Test
    fun testCheckDateBeforeEndDateButNotAfterStartDateReturnsFalse(){
        assertFalse(ProjectDateTimeUtils.checkIfDateIsInRange(dateBeforeButNotAfter,startDate,endDate))
    }

    @Test
    fun testCheckDateAfterStartDateButNotBeforeEndDateReturnsFalse(){
        assertFalse(ProjectDateTimeUtils.checkIfDateIsInRange(dateAfterButNotBefore,startDate,endDate))
    }

    @Test
    fun testCheckDateOnStartDateReturnsTrue(){
        assertTrue(ProjectDateTimeUtils.checkIfDateIsInRange(startDate,startDate,endDate))
    }

    @Test
    fun testCheckDateOnEndDateReturnsTrue(){
        assertTrue(ProjectDateTimeUtils.checkIfDateIsInRange(endDate,startDate,endDate))
    }



}
