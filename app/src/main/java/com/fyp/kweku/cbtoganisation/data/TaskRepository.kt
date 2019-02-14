package com.fyp.kweku.cbtoganisation.data

import com.fyp.kweku.cbtoganisation.data.model.TaskMapper
import com.fyp.kweku.cbtoganisation.domain.Model.Task
import com.fyp.kweku.cbtoganisation.domain.Repository.ITaskRepository

class TaskRepository(var taskDao: TaskDao) : ITaskRepository {


    override fun getAlltasks(): List<Task> {
        val taskMapper = TaskMapper()
        return this.taskDao.loadAllTasks().map { taskMapper.fromEntity(it) }
    }

    override fun getTaskById(taskId: Int): Task {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveTask() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}