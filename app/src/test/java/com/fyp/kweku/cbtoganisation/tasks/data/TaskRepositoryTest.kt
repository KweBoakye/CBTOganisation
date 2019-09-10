package com.fyp.kweku.cbtoganisation.tasks.data

import com.fyp.kweku.cbtoganisation.tasks.data.model.TaskDataModel
import com.fyp.kweku.cbtoganisation.tasks.data.model.TaskMapper
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TaskRepositoryTest{

    private lateinit var taskDao: TaskDao
    private lateinit var taskRepository: TaskRepository
    private lateinit var taska: Task
    private lateinit var taskb: Task

    @BeforeEach
    fun init(){
        taskDao = mockk(relaxed = true)
        taskRepository = TaskRepository(taskDao)
        taska = mockk(relaxed = true)
        taskb = mockk(relaxed = true)


    }

    @Test
    fun testGetAllTasksReturnType(){
        val tasks =runBlocking { taskRepository.getAllTasks()}
        assertTrue(tasks is List<Task>)
    }

    @Test
    fun testsGetAllTasksCallsDAOMethod(){
       runBlocking {   taskRepository.getAllTasks()}
        verify(exactly = 1) {taskDao.loadAllTasks()}
    }

    @Test
    suspend fun testSaveTaskCallsDAOMethod(){
        taskRepository.saveTask(taska, taskb)
        val taskMapper = TaskMapper()
        val taskaMapped = taskMapper.toEntity(taska)
        val taskbMapped = taskMapper.toEntity(taskb)
        val taskArray: Array<TaskDataModel> = arrayOf(taskaMapped, taskbMapped)
        verify(exactly = 1) {taskDao.insertTasks(*taskArray)}

    }
}