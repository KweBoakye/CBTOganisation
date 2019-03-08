package com.fyp.kweku.cbtoganisation.tasks.data.model

import com.fyp.kweku.cbtoganisation.tasks.domain.model.TaskCategory
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.ModelMapper

class TaskCategoryMapper :
    ModelMapper<TaskCategoryDataModel, TaskCategory> {
    override fun fromEntity(from: TaskCategoryDataModel) =
        TaskCategory(
            from.taskCategoryID,
            from.taskCategory
        )

    override fun toEntity(from: TaskCategory) =
        TaskCategoryDataModel(
            from.taskCategoryID,
            from.taskCategory
        )
}