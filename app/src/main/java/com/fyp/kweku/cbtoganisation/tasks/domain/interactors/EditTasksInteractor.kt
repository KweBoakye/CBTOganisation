package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.TaskRepositoryInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import javax.inject.Inject

class EditTasksInteractor @Inject constructor(private val taskRepositoryInterface: TaskRepositoryInterface): EditTasksInteractorInterface {

    private val dateFormatter = ProjectDateTimeUtils.getCustomDateFormatter()
    private val scope = CoroutineScope(Job() + Dispatchers.Main)

  private fun updateTask(task:Task)= scope.launch(Dispatchers.IO){
    taskRepositoryInterface.updateTask(task)
}

    override  fun receiveTaskInput(input: Array<String>){
        if (input.isNotEmpty()) {
            val task = Task(
                input[0], input[1], input[2], input[3],
                LocalDate.parse(input[4], dateFormatter),
                LocalDate.parse(input[5],dateFormatter),
                LocalTime.parse(input[6]),
                LocalTime.parse(input[7])
            )
            updateTask(task)
        }
    }
}