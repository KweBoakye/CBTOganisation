package com.fyp.kweku.cbtoganisation.domain.interactors

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.GetAllTasksInteractor
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.TaskRepositoryInterface
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS) //generate new test ins
class GetAllTasksInteractorTest {

    private lateinit var getAllTasksInteractor: GetAllTasksInteractor
    private lateinit var taskrepositorymock: TaskRepositoryInterface

    @BeforeEach
    fun init() {
        taskrepositorymock = mockk(relaxed = true)
        getAllTasksInteractor =
            GetAllTasksInteractor(taskrepositorymock)
    }

    @Test
    fun repositoryLink() {
        getAllTasksInteractor.allTasks()
        verify(exactly = 1) { taskrepositorymock.getAlltasks() }
    }


}