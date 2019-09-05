package com.fyp.kweku.cbtoganisation.tasks.data.model


import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.ModelMapper
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter

class TaskMapper :
    ModelMapper<TaskDataModel, Task> {

    override fun fromEntity(from: TaskDataModel) = Task(
        from.taskID,
        from.taskName,
        from.taskLocation,
        from.taskDescription,
        LocalDate.parse(LocalDate.parse(from.taskStartDate).format( ProjectDateTimeUtils.getCustomDateFormatter()),ProjectDateTimeUtils.getCustomDateFormatter()),// from string to date
        LocalDate.parse(LocalDate.parse(from.taskEndDate).format( ProjectDateTimeUtils.getCustomDateFormatter()),ProjectDateTimeUtils.getCustomDateFormatter()),
        LocalTime.parse(from.taskStartTime),
        LocalTime.parse(from.taskEndTime)
    )

    override fun toEntity(from: Task) = TaskDataModel(
        from.taskID,
        from.taskName,
        from.taskLocation,
        from.taskDescription,
        from.taskStartDate.format(DateTimeFormatter.ISO_LOCAL_DATE),//from date to string
        from.taskEndDate.format(DateTimeFormatter.ISO_LOCAL_DATE),
        from.taskStartTime.toString(),
        from.taskEndTime.toString()
    )




}
