package com.fyp.kweku.cbtoganisation.tasks.presentation.deletetask

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.EditAndDeleteTasksInteractor
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.EditAndDeleteTasksInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.PresentationModelMapper
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DeleteTasksController(val editAndDeleteTasksInteractorInterface: EditAndDeleteTasksInteractorInterface) {

    val presentationModelMapper: PresentationModelMapper = PresentationModelMapper()

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    fun deleteTask(task: TaskPresentationModel) = scope.launch { editAndDeleteTasksInteractorInterface.deleteTask(presentationModelMapper.fromEntity(task)) }
}