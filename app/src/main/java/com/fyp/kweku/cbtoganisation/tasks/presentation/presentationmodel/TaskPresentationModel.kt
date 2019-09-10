package com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime

data class TaskPresentationModel(
    val taskID: String,
    val taskName: String,       /*Denotes task Name*/
    val taskLocation: String,   /*Denotes Location Task will take place */
    val taskDescription: String,/*Description of the task*/
    val taskStartDate: LocalDate,
    val taskEndDate: LocalDate,/* Date task will take place*/
    val taskStartTime: LocalTime, /* Time task will start*/
    val taskEndTime: LocalTime/* Length/duration of task*/

)

