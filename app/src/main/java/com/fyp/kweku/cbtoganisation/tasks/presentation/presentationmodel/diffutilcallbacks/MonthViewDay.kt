package com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.diffutilcallbacks

import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.threeten.bp.LocalDate

data class MonthViewDay(val date: LocalDate,
                        val partOfCurrentMonth:Boolean,
                        val taskList: List<TaskPresentationModel?>) {
}