package com.fyp.kweku.cbtoganisation.tasks.data

import android.app.Application
import androidx.annotation.WorkerThread
import com.fyp.kweku.cbtoganisation.tasks.data.model.TaskMapper
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.TaskRepositoryInterface
import timber.log.Timber

class TaskRepository(val taskDao: TaskDao) : TaskRepositoryInterface {


    override suspend fun getAlltasks(): List<Task> {
        val taskMapper = TaskMapper()
        return this.taskDao.loadAllTasks().map { taskMapper.fromEntity(it) }
    }

    override suspend fun getTaskById(taskId: String): Task {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @WorkerThread
    override suspend fun saveTask(vararg tasks: Task) {
        val taskMapper = TaskMapper()
        val confirm: List<Long> = this.taskDao.insertTasks(*tasks.map { taskMapper.toEntity(it) }.toTypedArray())
        val conf = confirm[0]
        Timber.i("Repository Save Task Called $conf")

    }

    override suspend fun newTask(task: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}