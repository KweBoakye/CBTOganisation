package com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.GetTasksInteractorInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MonthCalendarController(val getTasksInteractorInterface: GetTasksInteractorInterface) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)


    fun loadAllTasksForRecycler() = scope.launch(Dispatchers.IO){ getTasksInteractorInterface.sendTasksToPresentationLayer()}
}