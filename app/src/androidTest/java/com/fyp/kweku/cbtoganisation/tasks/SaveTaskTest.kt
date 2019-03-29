package com.fyp.kweku.cbtoganisation.tasks

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fyp.kweku.cbtoganisation.tasks.data.TaskDao
import com.fyp.kweku.cbtoganisation.tasks.data.TaskRepository
import com.fyp.kweku.cbtoganisation.tasks.data.TestDatabase
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.CreateNewTaskInteractor
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.CreateNewTaskInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.TaskRepositoryInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask.CreateNewTaskController
import com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask.CreateNewTaskFragment
import com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask.CreateNewTaskViewClassInterface
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
open class SaveTaskTest: TestDatabase() {


    private lateinit var task: Task
    private lateinit var createNewTaskController: CreateNewTaskController
    private lateinit var createNewTaskInteractorInterface: CreateNewTaskInteractorInterface
    private lateinit var taskRepositoryInterface: TaskRepositoryInterface
    private lateinit var taskDao: TaskDao
    private lateinit var createNewTaskViewClassInterface: CreateNewTaskViewClassInterface
    private lateinit var createNewTaskFragment: CreateNewTaskFragment
    private lateinit var scenario: FragmentScenario<CreateNewTaskFragment>



    @Before
    fun init(){
        task = Task("testID",
            "TestName",
            "Test Location",
            "Test Description",
            "12/03/2002",
            "10:00",
            "12:00")
scenario = launchFragmentInContainer<CreateNewTaskFragment>()
        createNewTaskFragment = CreateNewTaskFragment()
        taskDao = appDatabase.taskDao()
        taskRepositoryInterface = TaskRepository(taskDao)
        createNewTaskInteractorInterface= CreateNewTaskInteractor(taskRepositoryInterface)
        createNewTaskController = CreateNewTaskController(createNewTaskInteractorInterface)
    }

    @Test
    fun placeHolder(){}

}