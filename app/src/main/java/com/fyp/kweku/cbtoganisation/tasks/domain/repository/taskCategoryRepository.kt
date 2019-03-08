package com.fyp.kweku.cbtoganisation.tasks.domain.repository

import com.fyp.kweku.cbtoganisation.tasks.domain.model.TaskCategory

interface taskCategoryRepository {
    fun SaveTaskCatgory()
    fun getAlltaskCategories(): List<TaskCategory>
    fun getTaskCategoriesById(taskId: Int): TaskCategory

}