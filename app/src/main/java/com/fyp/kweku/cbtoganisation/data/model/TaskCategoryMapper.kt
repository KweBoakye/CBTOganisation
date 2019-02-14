package com.fyp.kweku.cbtoganisation.data.model

import com.fyp.kweku.cbtoganisation.domain.Model.TaskCategory
import com.fyp.kweku.cbtoganisation.domain.Repository.ModelMapper

class TaskCategoryMapper: ModelMapper<TaskCategoryDataModel, TaskCategory> {
    override fun fromEntity(from: TaskCategoryDataModel)= TaskCategory(from.taskCategory)
    override fun toEntity(from: TaskCategory)= TaskCategoryDataModel(from.taskCategory)
}