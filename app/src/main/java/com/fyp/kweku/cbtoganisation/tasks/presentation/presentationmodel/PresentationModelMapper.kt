package com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel

import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.ModelMapper

class PresentationModelMapper:  ModelMapper<TaskPresentationModel, Task> {


    override fun fromEntity(from: TaskPresentationModel): Task = Task(
        from.taskID,
        from.taskName,
        from.taskLocation,
        from.taskDescription,
        from.taskDate,
        from.taskStartTime,
        from.taskEndTime
    )

    override fun toEntity(from: Task): TaskPresentationModel =
        TaskPresentationModel(
            from.taskID,
            from.taskName,
            from.taskLocation,
            from.taskDescription,
            from.taskDate,
            from.taskStartTime,
            from.taskEndTime
        )
}