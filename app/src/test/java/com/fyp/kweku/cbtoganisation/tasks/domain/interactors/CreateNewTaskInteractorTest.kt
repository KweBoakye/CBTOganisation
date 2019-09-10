package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TaskOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.TaskRepositoryInterface
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreateNewTaskInteractorTest {

    private lateinit var createNewTaskInteractor: CreateNewTaskInteractor
    private lateinit var taskRepositoryInterfaceMock: TaskRepositoryInterface
    private lateinit var taskMock: Task
    private lateinit var taskOutput: TaskOutput

    @BeforeEach
    fun init() {
        taskMock = mockk(relaxed = true)
        taskRepositoryInterfaceMock = mockk(relaxed = true)
        createNewTaskInteractor =
            CreateNewTaskInteractor(taskRepositoryInterfaceMock, taskOutput )
    }

    @Test
    fun testGeneratedIdIsString()
    {val testID = createNewTaskInteractor.generateTaskID()
        repeat(10)
        { assertNotEquals(testID,createNewTaskInteractor.generateTaskID())}
    }

    @Test
    fun testCreateNewTaskWithID(){
        val testNewTask =createNewTaskInteractor.createNewTaskObject(taskMock)
    assertTrue(testNewTask is Task)
        repeat(10)
        { assertNotEquals(testNewTask.taskID,createNewTaskInteractor.generateTaskID())}}

    @Test
     fun testSaveTasksCallsRepositoryMethod(){
        val output = runBlocking { createNewTaskInteractor.sendTaskToDataLayer(taskMock)}
        verify(exactly = 1) { runBlocking {taskRepositoryInterfaceMock.saveTask(output)} }
    }
}