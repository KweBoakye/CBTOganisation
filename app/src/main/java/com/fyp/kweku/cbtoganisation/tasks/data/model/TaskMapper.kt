package com.fyp.kweku.cbtoganisation.tasks.data.model


import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.model.TaskCategory
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.ModelMapper

class TaskMapper :
    ModelMapper<TaskDataModel, Task> {

    private var tcMapper = TaskCategoryMapper()
    override fun fromEntity(from: TaskDataModel) = Task(
        from.taskID,
        from.taskName,
        from.taskLocation,
        from.taskDescription,
        from.taskDate,
        from.taskStartTime,
        from.taskLength
    )

    override fun toEntity(from: Task) = TaskDataModel(
        from.taskID,
        from.taskName,
        from.taskLocation,
        from.taskDescription,
        from.taskDate,
        from.taskStartTime,
        from.taskLength
    )
}
