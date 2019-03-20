package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.GetTasksInteractor
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.TaskRepositoryInterface
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS) //generate new test ins
class GetTasksInteractorTest {

    private lateinit var getTasksInteractor: GetTasksInteractor
    private lateinit var taskrepositorymock: TaskRepositoryInterface
    private lateinit var getTasksInteracterOutput: GetTasksInteracterOutput
    private lateinit var task: Task

    @BeforeEach
    fun init() {
        taskrepositorymock = mockk(relaxed = true) //relasxdd as don't need to specify behavior allows stubbing
        getTasksInteracterOutput = mockk(relaxed = true)
        task = mockk(relaxed = true)
        getTasksInteractor = GetTasksInteractor(taskrepositorymock, getTasksInteracterOutput)
    }

    @Test
    fun testGetAllTasks() {
        getTasksInteractor.allTasks()
        verify(exactly = 1) { taskrepositorymock.getAlltasks() }
    }

  @Test
    fun testGetTaskByID() {
        getTasksInteractor.getTaskByID(task.taskID)
        verify(exactly = 1) { taskrepositorymock.getTaskById(task.taskID) }
    }

    @Test
    fun testSendSingleTaskToPresentationLayer(){
        getTasksInteractor.sendSingleTaskToPresentationLayer(task)
        verify(exactly = 1) { getTasksInteracterOutput.showTask(task) }
    }

    @Test
    fun testSendTasksToPresentationLayer(){
        val tasks = listOf(task,task)
        getTasksInteractor.sendTasksToPresentationLayer(tasks)
        verify(exactly = 1) { getTasksInteracterOutput.showAllTasks(tasks) }
    }



}