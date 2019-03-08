package com.fyp.kweku.cbtoganisation.tasks.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fyp.kweku.cbtoganisation.tasks.data.model.TaskDataModel

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTasks(vararg Tasks: TaskDataModel)

    @Query("SELECT * FROM tasks")
    fun loadAllTasks(): List<TaskDataModel>

}