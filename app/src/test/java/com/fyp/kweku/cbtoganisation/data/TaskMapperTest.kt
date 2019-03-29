package com.fyp.kweku.cbtoganisation.data


import com.fyp.kweku.cbtoganisation.tasks.data.model.TaskDataModel
import com.fyp.kweku.cbtoganisation.tasks.data.model.TaskMapper
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TaskMapperTest {
    private lateinit var inputTaskDataModel: TaskDataModel
    private lateinit var taskMapper: TaskMapper

    @BeforeEach
    fun init() {
        taskMapper = TaskMapper()
    }

    @Test
    fun fromTaskDataModel() {

        var inputTaskDataModel =
            TaskDataModel(
                "1",
                "s1",
                "s2",
                "s3",
                "11/01/2002",
                "10:00",
                "12:00"
            )
        var output = taskMapper.fromEntity(inputTaskDataModel)
        assertEquals(inputTaskDataModel.taskID, output.taskID)
        assertEquals(inputTaskDataModel.taskName, output.taskName)
        assertEquals(inputTaskDataModel.taskLocation, output.taskLocation)
        assertEquals(inputTaskDataModel.taskDescription, output.taskDescription)
        assertEquals(inputTaskDataModel.taskDate, output.taskDate)
        assertEquals(inputTaskDataModel.taskStartTime, output.taskStartTime)
        assertEquals(inputTaskDataModel.taskEndTime, output.taskEndTime)

    }

    @Test
    fun toTaskDataModel() {
        var inputTask = Task(
            "1",
            "s1",
            "s2",
            "s3",
            "11/01/2002",
            "10:00",
            "12:00"

        )
        var output = taskMapper.toEntity(inputTask)
        assertEquals(inputTask.taskID, output.taskID)
        assertEquals(inputTask.taskName, output.taskName)
        assertEquals(inputTask.taskLocation, output.taskLocation)
        assertEquals(inputTask.taskDescription, output.taskDescription)
        assertEquals(inputTask.taskDate, output.taskDate)
        assertEquals(inputTask.taskStartTime, output.taskStartTime)
        assertEquals(inputTask.taskEndTime, output.taskEndTime)

    }
}