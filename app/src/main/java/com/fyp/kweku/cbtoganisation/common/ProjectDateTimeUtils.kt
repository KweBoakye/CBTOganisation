package com.fyp.kweku.cbtoganisation.common

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class ProjectDateTimeUtils {
    companion object {
        fun getCustomDateFormatter(): DateTimeFormatter {
            return DateTimeFormatter.ofPattern("dd/MM/yyyy")

        }

        fun checkIfDateIsInRange(dateToCheck: LocalDate, startDate: LocalDate, endDate: LocalDate):Boolean{
            return dateToCheck.isEqual(startDate) || dateToCheck.isEqual(endDate) || dateToCheck.isAfter(startDate)  && dateToCheck.isBefore(endDate)
        }

    }
}