package com.fyp.kweku.cbtoganisation.data


import com.fyp.kweku.cbtoganisation.data.model.TaskCategoryDataModel
import com.fyp.kweku.cbtoganisation.data.model.TaskDataModel
import com.fyp.kweku.cbtoganisation.data.model.TaskMapper
import com.fyp.kweku.cbtoganisation.domain.Model.Task
import com.fyp.kweku.cbtoganisation.domain.Model.TaskCategory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
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
        var inputTaskCategoryDataModel = TaskCategoryDataModel("dog")
        var inputTaskDataModel =
            TaskDataModel(1, "s1", "s2", "s3", "11/01/2002", "10:00", 12, inputTaskCategoryDataModel)
        var output = taskMapper.fromEntity(inputTaskDataModel)
        assertEquals(inputTaskDataModel.taskID, output.taskID)
        assertEquals(inputTaskDataModel.taskName, output.taskName)
        assertEquals(inputTaskDataModel.taskLocation, output.taskLocation)
        assertEquals(inputTaskDataModel.taskDescription, output.taskDescription)
        assertEquals(inputTaskDataModel.taskDate, output.taskDate)
        assertEquals(inputTaskDataModel.taskStartTime, output.taskStartTime)
        assertEquals(inputTaskDataModel.taskLength, output.taskLength)
        assertEquals(inputTaskDataModel.taskCategoryDataModel.taskCategory, output.taskCategory.taskCategory)
        assertEquals(1, output.taskID)
        assertTrue(output.taskCategory is TaskCategory)
    }

    @Test
    fun toTaskDataModel() {

        var inputTaskCategory = TaskCategory("cat")
        var inputTask = Task(1, "s1", "s2", "s3", "11/01/2002", "10:00", 12, inputTaskCategory)
        var output = taskMapper.toEntity(inputTask)
        assertEquals(inputTask.taskID, output.taskID)
        assertEquals(inputTask.taskName, output.taskName)
        assertEquals(inputTask.taskLocation, output.taskLocation)
        assertEquals(inputTask.taskDescription, output.taskDescription)
        assertEquals(inputTask.taskDate, output.taskDate)
        assertEquals(inputTask.taskStartTime, output.taskStartTime)
        assertEquals(inputTask.taskLength, output.taskLength)
        assertEquals(inputTask.taskCategory.taskCategory, output.taskCategoryDataModel.taskCategory)
        assertEquals(1, output.taskID)
        assertTrue(output.taskCategoryDataModel is TaskCategoryDataModel)

    }
}