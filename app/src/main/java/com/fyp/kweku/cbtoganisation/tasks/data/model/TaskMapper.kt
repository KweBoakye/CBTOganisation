package com.fyp.kweku.cbtoganisation.tasks.data.model


import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.ModelMapper
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime

class TaskMapper :
    ModelMapper<TaskDataModel, Task> {

    private var tcMapper = TaskCategoryMapper()
    override fun fromEntity(from: TaskDataModel) = Task(
        from.taskID,
        from.taskName,
        from.taskLocation,
        from.taskDescription,
        LocalDate.parse(from.taskStartDate, ProjectDateTimeUtils.getCustomDateFormatter()),// from string to date
        LocalDate.parse(from.taskEndDate, ProjectDateTimeUtils.getCustomDateFormatter()),
        LocalTime.parse(from.taskStartTime),
        LocalTime.parse(from.taskEndTime),
        from.taskCompleted
    )

    override fun toEntity(from: Task) = TaskDataModel(
        from.taskID,
        from.taskName,
        from.taskLocation,
        from.taskDescription,
        from.taskStartDate.format(ProjectDateTimeUtils.getCustomDateFormatter()),//from date to string
        from.taskEndDate.format(ProjectDateTimeUtils.getCustomDateFormatter()),
        from.taskStartTime.toString(),
        from.taskEndTime.toString(),
        from.taskCompleted
    )
}
