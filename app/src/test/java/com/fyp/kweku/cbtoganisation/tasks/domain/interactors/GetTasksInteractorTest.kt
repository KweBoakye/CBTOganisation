package com.fyp.kweku.cbtoganisation.tasks.domain.interactors


import com.fyp.kweku.cbtoganisation.tasks.domain.TaskOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.TaskRepositoryInterface
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS) //generate new test ins
class GetTasksInteractorTest {

    private lateinit var getTasksInteractor: GetTasksInteractor
    private lateinit var taskrepositorymock: TaskRepositoryInterface
    private lateinit var taskOutput: TaskOutput
    private lateinit var task: Task

    @BeforeEach
    fun init() {
        taskrepositorymock = mockk(relaxed = true) //relasxdd as don't need to specify behavior allows stubbing
        taskOutput = mockk(relaxed = true)
        task = mockk(relaxed = true)
        getTasksInteractor = GetTasksInteractor(taskrepositorymock, taskOutput)
    }

    @Test
    fun testGetAllTasks() {
        runBlocking { getTasksInteractor.allTasks() }
        verify(exactly = 1) { runBlocking { taskrepositorymock.getAlltasks() }}
        }


  @Test
    fun testGetTaskByID() {
      runBlocking {getTasksInteractor.getTaskByID(task.taskID)}
          verify(exactly = 1) { runBlocking{taskrepositorymock.getTaskById(task.taskID) }}
    }

    @Test
    fun testSendSingleTaskToPresentationLayer(){
        getTasksInteractor.sendSingleTaskToPresentationLayer(task)
        verify(exactly = 1) { taskOutput.showTask(task) }
    }

    @Test
    fun testSendTasksToPresentationLayer(){
        val tasks = listOf(task,task)
        runBlocking{getTasksInteractor.sendTasksToPresentationLayer()}
        verify(exactly = 1) { runBlocking{taskOutput.showAllTasks(tasks) }}
    }



}