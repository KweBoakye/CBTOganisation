package com.fyp.kweku.cbtoganisation.tasks.data

import com.fyp.kweku.cbtoganisation.tasks.data.model.TaskMapper
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.TaskRepositoryInterface

class TaskRepository constructor(val taskDao: TaskDao) : TaskRepositoryInterface {


    override fun getAlltasks(): List<Task> {
        val taskMapper = TaskMapper()
        return this.taskDao.loadAllTasks().map { taskMapper.fromEntity(it) }
    }

    override fun getTaskById(taskId: String): Task {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveTask(vararg tasks: Task) {
        val taskMapper = TaskMapper()
        this.taskDao.insertTasks(*tasks.map { taskMapper.toEntity(it) }.toTypedArray())

    }

    override fun newTask(task: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /*companion object {
        @Volatile
        private var INSTANCE: TaskRepository? = null

        fun getInstance(taskDao: TaskDao): TaskRepository {
            return INSTANCE ?: synchronized(this) {
                TaskRepository(taskDao).also {
                    INSTANCE = it
                }
            }
        }
    }*/
}