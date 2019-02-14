package com.fyp.kweku.cbtoganisation.data.model


import com.fyp.kweku.cbtoganisation.domain.Model.Task
import com.fyp.kweku.cbtoganisation.domain.Repository.ModelMapper

class TaskMapper : ModelMapper<TaskDataModel, Task> {

    private var tcMapper = TaskCategoryMapper()
    override fun fromEntity(from: TaskDataModel) = Task(
        from.taskID,
        from.taskName,
        from.taskLocation,
        from.taskDescription,
        from.taskDate,
        from.taskStartTime,
        from.taskLength,
        tcMapper.fromEntity(from.taskCategoryDataModel)
    )

    override fun toEntity(from: Task) = TaskDataModel(
        from.taskID,
        from.taskName,
        from.taskLocation,
        from.taskDescription,
        from.taskDate,
        from.taskStartTime,
        from.taskLength,
        tcMapper.toEntity(from.taskCategory)
    )
}
