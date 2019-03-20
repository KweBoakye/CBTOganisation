package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.TaskRepositoryInterface
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreateNewTaskInteractorTest {

    private lateinit var createNewTaskInteractor: CreateNewTaskInteractor
    private lateinit var taskRepositoryInterfaceMock: TaskRepositoryInterface
    private lateinit var taskMock: Task

    @BeforeEach
    fun init() {
        taskMock = mockk(relaxed = true)
        taskRepositoryInterfaceMock = mockk(relaxed = true)
        createNewTaskInteractor =
            CreateNewTaskInteractor(taskRepositoryInterfaceMock )
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
        val a = createNewTaskInteractor.SendTaskToDataLayer(taskMock)
        verify(exactly = 1) { taskRepositoryInterfaceMock.saveTask(a) }
    }
}