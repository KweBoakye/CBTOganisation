package com.fyp.kweku.cbtoganisation.common

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class ProjectDateTimeUtils {
    // companion object, for Kotlin it can be used as a replacement java singleton
    companion object {

        //provides a Date Time Formatter for across the apllication
        fun getCustomDateFormatter(): DateTimeFormatter {
            return DateTimeFormatter.ofPattern("dd/MM/yyyy")

        }

        fun getDataDateFormatter():DateTimeFormatter{
            return DateTimeFormatter.ofPattern("yyyy-MM-dd")
        }

        //checks if a date is in a certain range provided a parameters
        fun checkIfDateIsInRange(dateToCheck: LocalDate, startDate: LocalDate, endDate: LocalDate):Boolean{
            return dateToCheck.isEqual(startDate) || dateToCheck.isEqual(endDate) || dateToCheck.isAfter(startDate)  && dateToCheck.isBefore(endDate)
        }

    }
}