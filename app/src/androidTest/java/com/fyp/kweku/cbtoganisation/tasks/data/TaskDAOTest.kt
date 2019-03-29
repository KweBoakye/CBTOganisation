package com.fyp.kweku.cbtoganisation.tasks.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fyp.kweku.cbtoganisation.tasks.data.model.TaskDataModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class TaskDAOTest:TestDatabase() {


    private lateinit var taskDataModel: TaskDataModel

    @Before
    fun init(){
        taskDataModel = TaskDataModel("testID",
            "TestName",
            "Test Location",
        "Test Description",
            "12/03/2002",
            "10:00",
        "12:00")
    }

    @Test
    fun testInsertTasks(){
        appDatabase.taskDao().insertTasks(taskDataModel)
        val retrievedTask= appDatabase.taskDao().findTaskById(taskDataModel.taskID)
        assertEquals(taskDataModel, retrievedTask)

    }

}