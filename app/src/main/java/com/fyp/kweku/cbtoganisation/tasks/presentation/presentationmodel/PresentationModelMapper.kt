package com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel

import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.ModelMapper
import org.threeten.bp.LocalDate

class PresentationModelMapper:  ModelMapper<TaskPresentationModel, Task> {


    override fun fromEntity(from: TaskPresentationModel): Task = Task(
        from.taskID,
        from.taskName,
        from.taskLocation,
        from.taskDescription,
        LocalDate.parse( from.taskStartDate.format(ProjectDateTimeUtils.getCustomDateFormatter()),ProjectDateTimeUtils.getCustomDateFormatter()),
        LocalDate.parse( from.taskEndDate.format(ProjectDateTimeUtils.getCustomDateFormatter()),ProjectDateTimeUtils.getCustomDateFormatter()),
        from.taskStartTime,
        from.taskEndTime,
        from.taskCompleted
    )

    override fun toEntity(from: Task): TaskPresentationModel =
        TaskPresentationModel(
            from.taskID,
            from.taskName,
            from.taskLocation,
            from.taskDescription,
            LocalDate.parse( from.taskStartDate.format(ProjectDateTimeUtils.getCustomDateFormatter()),ProjectDateTimeUtils.getCustomDateFormatter()),
            LocalDate.parse( from.taskEndDate.format(ProjectDateTimeUtils.getCustomDateFormatter()),ProjectDateTimeUtils.getCustomDateFormatter()),
            from.taskStartTime,
            from.taskEndTime,
            from.taskCompleted
        )
}