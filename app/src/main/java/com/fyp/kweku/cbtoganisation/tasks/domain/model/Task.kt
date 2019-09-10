package com.fyp.kweku.cbtoganisation.tasks.domain.model

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime

data class Task(
    val taskID: String,
    val taskName: String,
    val taskLocation: String,
    val taskDescription: String,
    val taskStartDate: LocalDate,
    val taskEndDate: LocalDate,
    val taskStartTime: LocalTime,
    val taskEndTime: LocalTime
 )



