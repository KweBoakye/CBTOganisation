package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import kotlinx.coroutines.Job

interface EditTasksInteractorInterface {

   fun receiveTaskInput(input: Array<String>)

}