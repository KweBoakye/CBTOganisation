package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import kotlinx.coroutines.Job

interface DeleteAndOrRestoreTaskInteractorInterface {

    fun restoreTask(): Job
    fun deleteAndOrRestoreTask(taskID: String): Job
}