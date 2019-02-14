package com.fyp.kweku.cbtoganisation.domain.interactors

import com.fyp.kweku.cbtoganisation.domain.Repository.ITaskRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GetAllTasksInteractorTest {

    private lateinit var getAllTasksInteractor: GetAllTasksInteractor
    private lateinit var taskrepositorymock: ITaskRepository

    @BeforeEach
    fun init() {
        taskrepositorymock = mockk(relaxed = true)
        getAllTasksInteractor = GetAllTasksInteractor(taskrepositorymock)
    }

    @Test
    fun repositoryLink() {
        getAllTasksInteractor.allTasks()
        verify(exactly = 1) { taskrepositorymock.getAlltasks() }
    }


}