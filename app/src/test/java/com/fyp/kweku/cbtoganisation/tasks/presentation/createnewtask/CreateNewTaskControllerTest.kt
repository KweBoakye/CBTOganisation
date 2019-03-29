package com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask

import com.fyp.kweku.cbtoganisation.tasks.data.TaskRepository
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.CreateNewTaskInteractor
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.CreateNewTaskInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.TaskRepositoryInterface
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreateNewTaskControllerTest{
    private lateinit var createNewTaskController: CreateNewTaskController
    private lateinit var createNewTaskViewClassInterface: CreateNewTaskViewClassInterface
    private lateinit var createNewTaskViewClass: CreateNewTaskViewClass
    private lateinit var createNewTaskInteractorInterface: CreateNewTaskInteractorInterface
    private lateinit var input: Array<String>
    private lateinit var taskRepositoryInterface: TaskRepositoryInterface


    @BeforeEach
    fun init() {createNewTaskViewClass = mockk(relaxed = true)
        createNewTaskViewClassInterface = mockk(relaxed = true)
        taskRepositoryInterface = mockk(relaxed = true)
        createNewTaskInteractorInterface = mockk(relaxed = true)

        input = arrayOf("s", "s", "s", "s", "s", "1")
        createNewTaskController = CreateNewTaskController(createNewTaskInteractorInterface)
        createNewTaskController.bindView(createNewTaskViewClassInterface)
    }

    @Test
    fun testPersistInput(){
    runBlocking { createNewTaskController.persistTask(input)}
        verify(exactly = 1){ runBlocking { createNewTaskInteractorInterface.SendTaskToDataLayer(any())}  }
    }

    @Test
    fun testCreatedTask(){

    }
}

