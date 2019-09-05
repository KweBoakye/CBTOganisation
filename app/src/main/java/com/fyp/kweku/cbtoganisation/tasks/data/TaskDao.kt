package com.fyp.kweku.cbtoganisation.tasks.data

import androidx.room.*
import com.fyp.kweku.cbtoganisation.tasks.data.model.TaskDataModel

@Dao

interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertTasks(vararg Tasks: TaskDataModel):List<Long>

    @Query("SELECT * FROM tasks")
    fun loadAllTasks(): List<TaskDataModel>

    @Query("SELECT * FROM  tasks WHERE taskID=:taskID")
    fun findTaskById(taskID: String): TaskDataModel

    @Query("SELECT taskLocation From tasks")
    fun loadAllLocations():List<String>

    @Query("SELECT * FROM tasks WHERE taskLocation=:taskLocation")
    fun getTasksByLocation(taskLocation: String):List<TaskDataModel>

    @Query("SELECT * FROM tasks WHERE ( taskStartDate BETWEEN:startDate AND :endDate) OR (taskEndDate BETWEEN :startDate AND :endDate)")
    fun filterTasksBy42DayCalendarMonth(startDate: String, endDate:String): List<TaskDataModel>

    @Delete
    fun deleteTask(task: TaskDataModel):Int

    @Update
    fun updateTask(task: TaskDataModel)



}