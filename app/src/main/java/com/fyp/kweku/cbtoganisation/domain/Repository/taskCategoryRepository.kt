package com.fyp.kweku.cbtoganisation.domain.Repository

import com.fyp.kweku.cbtoganisation.domain.Model.TaskCategory

interface taskCategoryRepository {
    fun SaveTaskCatgory()
    fun getAlltaskCategories(): List<TaskCategory>
    fun getTaskCategoriesById(taskId: Int): TaskCategory

}