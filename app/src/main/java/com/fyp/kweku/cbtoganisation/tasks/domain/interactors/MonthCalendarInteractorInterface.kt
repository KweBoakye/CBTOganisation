package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth

interface MonthCalendarInteractorInterface {

    suspend fun generateCalendarData(month: YearMonth):List<Triple<LocalDate, Boolean, MutableList<String>>>
}