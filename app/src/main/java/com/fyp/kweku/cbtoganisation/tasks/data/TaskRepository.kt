package com.fyp.kweku.cbtoganisation.tasks.data


import androidx.annotation.WorkerThread
import com.fyp.kweku.cbtoganisation.tasks.data.model.TaskMapper
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.TaskRepositoryInterface
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import timber.log.Timber

class TaskRepository(private val taskDao: TaskDao) : TaskRepositoryInterface {


    private val taskMapper = TaskMapper()

    override suspend fun getAllTasks(): List<Task> {

        return this.taskDao.loadAllTasks().map { taskMapper.fromEntity(it) }
    }

    override suspend fun getTaskById(taskId: String): Task {
        return taskMapper.fromEntity(this.taskDao.findTaskById(taskId))
    }

    @WorkerThread
    override suspend fun saveTask(vararg tasks: Task) {
        val taskMapper = TaskMapper()
        val confirm: List<Long> = this.taskDao.insertTasks(*tasks.map { taskMapper.toEntity(it) }.toTypedArray())
        val conf = confirm[0]
        Timber.i("Repository Save Task Called $conf")

    }

    override suspend fun getTaskBy42CalendarMonth(startDate: LocalDate, endDate: LocalDate): List<Task>{
        val t = taskDao.filterTasksBy42DayCalendarMonth(startDate.format(
            DateTimeFormatter.ISO_LOCAL_DATE),
            endDate.format(DateTimeFormatter.ISO_LOCAL_DATE))
        Timber.i("$t")
        return t
            .map { taskMapper.fromEntity(it) }
    }

    override suspend fun getAllLocations(): List<String> {
      return this.taskDao.loadAllLocations()
    }

    override suspend fun getTasksByLocation(taskLocation: String): List<Task> {
        return this.taskDao.getTasksByLocation(taskLocation).map { taskMapper.fromEntity(it) }
    }

    override suspend fun deleteTask(task: Task):Int {
       val t =taskDao.deleteTask(taskMapper.toEntity(task))
        Timber.i("$t")
        return t
    }

  override  suspend fun updateTask(task: Task){
       taskDao.updateTask(taskMapper.toEntity(task))
   }

}